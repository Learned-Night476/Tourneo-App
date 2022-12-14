package com.techelevator.controller;

import javax.validation.Valid;

import com.techelevator.dao.*;
import com.techelevator.model.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.techelevator.security.jwt.JWTFilter;
import com.techelevator.security.jwt.TokenProvider;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class AuthenticationController {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private UserDao userDao;
    private PlayerDao playerDao;
    private TournamentsDao tournamentsDao;
    private TournamentUsersDao tournamentUsersDao;
    private TournamentMatchDao tournamentMatchDao;
    private TournamentTypeDao tournamentTypeDao;
    private TournamentMessageDao tournamentMessageDao;
    private WhisperDao whisperDao;

    public AuthenticationController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder, UserDao userDao, PlayerDao playerDao, TournamentsDao tournamentsDao, TournamentUsersDao tournamentUsersDao, TournamentMatchDao tournamentMatchDao, TournamentTypeDao tournamentTypeDao, TournamentMessageDao tournamentMessageDao, WhisperDao whisperDao) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userDao = userDao;
        this.playerDao = playerDao;
        this.tournamentsDao = tournamentsDao;
        this.tournamentUsersDao = tournamentUsersDao;
        this.tournamentMatchDao = tournamentMatchDao;
        this.tournamentTypeDao = tournamentTypeDao;
        this.tournamentMessageDao = tournamentMessageDao;
        this.whisperDao = whisperDao;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginDto loginDto) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.createToken(authentication, false);
        
        User user = userDao.findByUsername(loginDto.getUsername());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
        return new ResponseEntity<>(new LoginResponseDto(jwt, user), httpHeaders, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void register(@Valid @RequestBody RegisterUserDto newUser) {
        try {
            User user = userDao.findByUsername(newUser.getUsername());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User Already Exists.");
        } catch (UsernameNotFoundException e) {
            userDao.create(newUser.getUsername(),newUser.getPassword(), newUser.getRole());
        }
    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "/profile/{userId}", method = RequestMethod.GET)
    public Player getProfile(@PathVariable int userId) {
        Player player = playerDao.getPlayer(userId);
        return player;

    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "/players/{username}", method = RequestMethod.GET)
    public Player getPlayerByUserName(@PathVariable String username) {
        Player player = playerDao.getPlayerByUsername(username);
        return player;

    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "/tournaments/{id}", method = RequestMethod.GET)
    public Tournaments getTournamentById(@PathVariable int id) {
        Tournaments tournament = tournamentsDao.getTournament(id);
        return tournament;

    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "tournamentType/tournaments/{id}", method = RequestMethod.GET)
    public TournamentType getTournamentType(@PathVariable int id) {
        TournamentType tournament = tournamentTypeDao.getTournamentTypeDao(id);
        return tournament;

    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "/messages/{id}", method = RequestMethod.GET)
    public TournamentMessage getMessageById(@PathVariable int id) {
        TournamentMessage tournamentMessage = tournamentMessageDao.getMessageByMessageId(id);
        return tournamentMessage;
    }

    @PreAuthorize("permitAll")
    @RequestMapping(value = "/tournaments", method = RequestMethod.POST)
    public void createTournament(@Valid @RequestBody Tournaments tournament) {
        tournamentsDao.createTournament(tournament);

    }

    @PreAuthorize("permitAll")
    @RequestMapping(value = "/tournaments/user/{id}", method = RequestMethod.GET)
    public List<Tournaments> getMyTournaments(@PathVariable int id) {
        return tournamentsDao.getTournamentsByAdmin(id);

    }

//    @PreAuthorize("permitAll")
//    @RequestMapping(path = "/tournaments/users/{userId}/", method = RequestMethod.GET)
//    public List<Tournaments> getPlayerTournaments(@PathVariable int userId) {
//        return tournamentsDao.getTournamentsByUserId(userId);
//
//    }

    @PreAuthorize("permitAll")
    @RequestMapping(value = "/tournaments/tournamentUsers", method = RequestMethod.POST)
    public void createTournamentUsers(@RequestBody @Valid TournamentUser tournamentUser) {
        tournamentUsersDao.createTournamentUser(tournamentUser);

    }

    @PreAuthorize("permitAll")
    @RequestMapping(value = "/tournaments/{tournamentId}/matches", method = RequestMethod.PUT)
    public void updateTournamentMatchWinner(@PathVariable int tournamentId, int winner) {
        tournamentMatchDao.updateTournamentMatch(winner);
    }


    @PreAuthorize("permitAll")
    @RequestMapping(path = "/tournaments", method = RequestMethod.GET)
    public List<Tournaments> listTournaments() {
        return tournamentsDao.getAllTournaments();

    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "/tournaments/{tournamentId}/{round}", method = RequestMethod.GET)
    public List<TournamentMatch> getTournamentMatchesByRoundAndId(@PathVariable int tournamentId, @PathVariable int round) {
        return tournamentMatchDao.getTournamentMatchesByTournamentIdAndRound(tournamentId, round);

    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "/tournaments/{tournamentId}/messages", method = RequestMethod.GET)
    public List<TournamentMessage> getTournamentMessages(@PathVariable int tournamentId) {
        return tournamentMessageDao.getTournamentMessagesByTournamentId(tournamentId);
    }

    @PreAuthorize("permitAll")
    @RequestMapping(value = "/messages", method = RequestMethod.POST)
    public void createMessage(@RequestBody TournamentMessage tournamentMessage) {
        tournamentMessageDao.createTournamentMessage(tournamentMessage);

    }




    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public List<User> listUsers() {
        List<User> list = new ArrayList<>();
        return userDao.findAll();

    }

    @RequestMapping(path = "/players", method = RequestMethod.GET)
    public List<Player> listPlayers() {
        List<User> list = new ArrayList<>();
        return playerDao.getAllPlayers();

    }

    @RequestMapping(path = "tournamentUsers/{tournamentId}", method = RequestMethod.GET)
    public List<TournamentUser> listTournamentUsers(@PathVariable int tournamentId) {
        List<TournamentUser> list = new ArrayList<>();
        return tournamentUsersDao.getTournamentUsers(tournamentId);

    }

    @RequestMapping(path = "/matches", method = RequestMethod.POST)
    public void createMatch(@Valid @RequestBody TournamentMatch tournamentMatch) {
        tournamentMatchDao.createTournamentMatch(tournamentMatch);
    }

    @PreAuthorize("permitAll")
    @RequestMapping(value = "/playerId/{username}", method = RequestMethod.GET)
    public int getPlayerIdByUsername(@PathVariable String username) {
        return playerDao.getPlayerIdByUsername(username);

    }

    @PreAuthorize("permitAll")
    @RequestMapping(value = "/{playerId}/{tournamentId}", method = RequestMethod.POST)
    public void createTournamentUser(@PathVariable int playerId, @PathVariable int tournamentId, @RequestBody @Valid TournamentUser tournamentUser) {
        tournamentUsersDao.createTournamentUser(tournamentUser);
    }

    @PreAuthorize("permitAll")
    @RequestMapping(value = "/whispers", method = RequestMethod.POST)
    public void createWhisper(@Valid @RequestBody Whisper whisper) {
        whisperDao.createWhisper(whisper);
    }

    @PreAuthorize("permitAll")
    @RequestMapping(value = "/whispers/{playerId}", method = RequestMethod.GET)
    public List<Whisper> getWhispersByPlayerId(@PathVariable int playerId) {
       return whisperDao.getWhispersByPlayerId(playerId);
    }

    @RequestMapping(value = "/players/tournament/{tournamentId}", method = RequestMethod.GET)
    public List<Player> getPlayersByTournamentId(@PathVariable int tournamentId) {
        return playerDao.getPlayersByTournamentId(tournamentId);
    }

    @PreAuthorize("permitAll")
    @RequestMapping (path = "/tournaments/participated/{userId}", method = RequestMethod.GET )
    public List<Tournaments> getTournamentsParticipated(@PathVariable int userId) {
        return tournamentsDao.getTournamentsByUserId(userId);
    }


    @RequestMapping(path = "players/{playerId}/{wins}", method = RequestMethod.PUT)
    public Integer updatePlayersWinsById(@PathVariable int playerId, @PathVariable int wins){
        return playerDao.updatePlayersWinsById(wins, playerId);
    }

    @RequestMapping(path = "players/playerLosses/{playerId}/{losses}", method = RequestMethod.PUT)
    public Integer updatePlayersLossesById(@PathVariable int playerId, @PathVariable int losses){
        return playerDao.updatePlayersLossesById(losses, playerId);
    }

    @RequestMapping(value = "/players/player/{playerId}", method = RequestMethod.GET)
    public Player getPlayerByPlayerId(@PathVariable int playerId) {
        return playerDao.getPlayerByPlayerId(playerId);

    }


    @RequestMapping(path = "tournamentStatus/{tournamentId}", method = RequestMethod.PUT)
    public Integer markTournamentCompleted(@PathVariable int tournamentId){
        return tournamentsDao.markTournamentCompleted(tournamentId);
    }

    @RequestMapping(path = "tournamentWinner/{tournamentId}/{username}", method = RequestMethod.PUT)
    public Integer setTournamentWinner(@PathVariable int tournamentId, @PathVariable String username){
        return tournamentsDao.setTournamentWinner(username, tournamentId);
    }



}

