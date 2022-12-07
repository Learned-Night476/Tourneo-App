package com.techelevator.dao;

import com.techelevator.model.TournamentUser;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcTournamentUsers implements TournamentUsersDao {

    private final JdbcTemplate jdbcTemplate;


    public JdbcTournamentUsers(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean createTournamentUser(int tournamentId, int playerId) {

        String sql = "INSERT INTO tournament_users (tournament_id, player_id) values (?, ?); ";

        jdbcTemplate.update(sql, tournamentId, playerId);

        return true;

    }

}
