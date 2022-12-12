package com.techelevator.dao;

import com.techelevator.model.Player;
import com.techelevator.model.TournamentUser;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTournamentUsers implements TournamentUsersDao {

    private final JdbcTemplate jdbcTemplate;


    public JdbcTournamentUsers(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean createTournamentUser(int tournamentId, int playerId) {

        String sql = "INSERT INTO tournament_users (tournament_id, player_id, isOut) values (?, ?, false); ";

        jdbcTemplate.update(sql, tournamentId, playerId);

        return true;

    }

    @Override
    public List<TournamentUser> getTournamentUsers(int tournamentId) {

        List<TournamentUser> tournamentUsers = new ArrayList<>();
        String sql = "select players.player_id, tournament_id, isOut, username FROM players join tournament_users on players.player_id = tournament_users.player_id WHERE tournament_id = ?";
        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, tournamentId);

        while (rs.next()) {
            TournamentUser tournamentUser = mapRowToTournamentUsersWithUsername(rs);
            tournamentUsers.add(tournamentUser);
        }

        return tournamentUsers;
    }

    private TournamentUser mapRowToTournamentUser(SqlRowSet rs) {

        TournamentUser tournamentUser = new TournamentUser();
        tournamentUser.setTournamentId(rs.getInt("tournament_id"));
        tournamentUser.setPlayerId(rs.getInt("player_id"));
        tournamentUser.setOut(rs.getBoolean("isOut"));

        return tournamentUser;

    }

    private TournamentUser mapRowToTournamentUsersWithUsername(SqlRowSet rs) {

        TournamentUser tournamentUser = new TournamentUser();
        tournamentUser.setTournamentId(rs.getInt("tournament_id"));
        tournamentUser.setPlayerId(rs.getInt("player_id"));
        tournamentUser.setOut(rs.getBoolean("isOut"));
        tournamentUser.setUsername(rs.getString("username"));

        return tournamentUser;

    }

}
