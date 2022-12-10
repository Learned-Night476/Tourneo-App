package com.techelevator.dao;

import com.techelevator.model.Player;
import com.techelevator.model.TournamentMatch;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTournamentMatchDao implements TournamentMatchDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTournamentMatchDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void createTournamentMatch(TournamentMatch tournamentMatch) {
        String sql = "insert into tournament_match (round, winner, tournament_id, player_id, away_player_id, player_username, away_player_username) values (?, ?, ?, ?, ?, ?, ?) returning match_id;";
        Integer matchId = jdbcTemplate.queryForObject(sql, Integer.class,
                tournamentMatch.getRound(), tournamentMatch.getWinner(), tournamentMatch.getTournamentId(), tournamentMatch.getPlayerId(), tournamentMatch.getAwayPlayerId(), tournamentMatch.getPlayerUsername(), tournamentMatch.getAwayPlayerUsername());


        }

    @Override
    public List<TournamentMatch> getTournamentMatchsByTournamentIdAndRound(int tournamentId, int round) {
        List<TournamentMatch> matches = new ArrayList<>();
        String sql = "Select match_id, round, winner, tournament_id, player_id, away_player_id from tournament_match WHERE round = ? and tournament_id = ?";


        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, tournamentId, round);

        while (rs.next()) {
            TournamentMatch match = mapRowToTournament(rs);
            matches.add(match);
        }

        return matches;

    }

    private TournamentMatch mapRowToTournament(SqlRowSet rs) {
        TournamentMatch tournamentMatch = new TournamentMatch();

        tournamentMatch.setMatchId(rs.getInt("match_id"));
        tournamentMatch.setAwayPlayerId(rs.getInt("away_player_id"));
        tournamentMatch.setPlayerId(rs.getInt("player_id"));
        tournamentMatch.setRound(rs.getInt("round"));
        tournamentMatch.setWinner(rs.getInt("winner"));
        tournamentMatch.setTournamentId(rs.getInt("tournament_id"));

        return tournamentMatch;
    }


}

