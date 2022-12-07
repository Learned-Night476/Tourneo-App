package com.techelevator.dao;

import com.techelevator.model.Tournaments;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

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
    public Tournaments createTournament(Tournaments tournament) {
        String sql = "insert into tournaments (participants, winner, admin_user, tournament_status, tournament_type, tournament_name) values (?, ?, ?, ?, ?, ?); ";

        Integer newUserId = jdbcTemplate.queryForObject(sql, Integer.class, tournament.getParticipants(), tournament.getWinner(), tournament.getAdminUser(), tournament.getTournamentStatus(), tournament.getTournamentTypeId(), tournament.getTournamentName());

        return getTournament(newUserId);

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
