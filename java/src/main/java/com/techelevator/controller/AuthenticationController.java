package com.techelevator.controller;

import javax.validation.Valid;

import com.techelevator.dao.PlayerDao;
import com.techelevator.dao.TournamentsDao;
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

import com.techelevator.dao.UserDao;
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

    public AuthenticationController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder, UserDao userDao, PlayerDao playerDao, TournamentsDao tournamentsDao) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userDao = userDao;
        this.playerDao = playerDao;
        this.tournamentsDao = tournamentsDao;
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
    @RequestMapping(value = "/tournaments", method = RequestMethod.POST)
    public Tournaments createTournament(@Valid @RequestBody Tournaments tournament) {
        return tournamentsDao.createTournament(tournament);

    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "/tournaments", method = RequestMethod.GET)
    public List<Tournaments> listTournaments() {
        return tournamentsDao.getAllTournaments();

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


}

