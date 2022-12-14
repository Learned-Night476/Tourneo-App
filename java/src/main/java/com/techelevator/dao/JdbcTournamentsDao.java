package com.techelevator.dao;

import com.techelevator.model.Player;
import com.techelevator.model.Tournaments;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTournamentsDao implements TournamentsDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTournamentsDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Tournaments getTournament(int id) {
        String sql = "SELECT * FROM tournaments WHERE tournament_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        if (results.next()) {
            return mapRowToTournament(results);
        } else {
            return null;
        }

    }



    @Override
    public List<Tournaments> getAllTournaments() {

        List<Tournaments> tournaments = new ArrayList<>();

        String sql = "SELECT * FROM tournaments";

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql);

        while (rs.next()) {
            Tournaments tournament = mapRowToTournament(rs);
            tournaments.add(tournament);
        }
        return tournaments;
    }

    @Override
    public List<Tournaments> getTournamentsByAdmin(int id) {
        List<Tournaments> tournaments = new ArrayList<>();

        String sql = "SELECT * FROM tournaments WHERE admin_user = ? ";

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, id);

        while (rs.next()) {
            Tournaments tournament = mapRowToTournament(rs);
            tournaments.add(tournament);
        }

        return tournaments;
    }

    @Override
    public List<Tournaments> getTournamentsByUserId(int id) {
        List<Tournaments> tournaments = new ArrayList<>();

        String sql = "SELECT tournaments.tournament_id, participants, winner, admin_user, tournament_status, tournament_type, tournament_name " +
                "FROM tournaments " +
                "JOIN tournament_users ON tournaments.tournament_id = tournament_users.tournament_id " +
                "join players on tournament_users.player_id = players.player_id " +
                "join users on players.user_id = users.user_id " +
                "WHERE users.user_id = ?; ";

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, id);

        while (rs.next()) {
            Tournaments tournament = mapRowToTournament(rs);
            tournaments.add(tournament);
        }

        return tournaments;
    }

    @Override
    public boolean createTournament(Tournaments tournament) {
        String sql = "insert into tournaments (participants, winner, admin_user, tournament_status, tournament_type, tournament_name) values (?, ?, ?, ?, ?, ?); ";

        Integer newUserId = jdbcTemplate.queryForObject(sql, Integer.class, tournament.getParticipants(), tournament.getWinner(), tournament.getAdminUser(), tournament.getTournamentStatus(), tournament.getTournamentTypeId(), tournament.getTournamentName());

        return true;

    }

    @Override
    public Integer markTournamentCompleted(int tournamentId) {
        String sql = "update tournaments set tournament_status = 'Completed' where tournament_id = ?;";
        Integer placeHolder = jdbcTemplate.queryForObject(sql, Integer.class, tournamentId);

        return placeHolder;
    }


    private Tournaments mapRowToTournament(SqlRowSet rs) {
        Tournaments tournament = new Tournaments();
        tournament.setAdminUser(rs.getInt("admin_user"));
        tournament.setParticipants(rs.getInt("participants"));
        tournament.setTournamentName(rs.getString("tournament_name"));
        tournament.setTournamentId(rs.getInt("tournament_id"));
        tournament.setTournamentStatus(rs.getString("tournament_status"));
        tournament.setWinner(rs.getInt("winner"));
        tournament.setTournamentTypeId(rs.getInt("tournament_type"));

        return tournament;

    }

}
