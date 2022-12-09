package com.techelevator.dao;

import com.techelevator.model.TournamentMatch;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcTournamentMatchDao implements TournamentMatchDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTournamentMatchDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void createTournamentMatch(TournamentMatch tournamentMatch) {
        String sql = "insert into tournament_match (round, winner, tournament_id, player_id, away_player_id) values (?, ?, ?, ?, ?) returning match_id;";
        Integer matchId = jdbcTemplate.queryForObject(sql, Integer.class,
                tournamentMatch.getRound(), tournamentMatch.getWinner(), tournamentMatch.getTournamentId(), tournamentMatch.getPlayerId(), tournamentMatch.getAwayPlayerId());


        }
    }
//    public boolean createTournamentMatch(TournamentMatch tournamentMatch) {
//
//        String sql = "insert into tournament_match (home, away, round, tournament_id) values (?, ?, ?, ?); ";
//
//        Integer newUserId = jdbcTemplate.queryForObject(sql, Integer.class, tournamentMatch.getHomeId(), tournamentMatch.getAwayId(), tournamentMatch.getRound(), tournamentMatch.getTournamentId());
//
//        return true;
//
//    }
//}
