package com.techelevator.dao;

import com.techelevator.model.TournamentMatch;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTournamentMatchDao implements TournamentMatchDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTournamentMatchDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public boolean createTournamentMatch(int tournamentId, int homePlayerId, int awayPlayerId, int round ) {

        String sql = "insert into tournament_match (home, away, round, tournament_id) values ( ?, ?, ?, ?); ";

        Integer newUserId = jdbcTemplate.queryForObject(sql, Integer.class, homePlayerId, awayPlayerId, round, tournamentId);

        return true;

    }
}
