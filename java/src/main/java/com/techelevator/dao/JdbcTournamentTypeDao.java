package com.techelevator.dao;

import com.techelevator.model.TournamentType;
import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component

public class JdbcTournamentTypeDao implements TournamentTypeDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTournamentTypeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public TournamentType getTournamentTypeDao(int id) {
        String sql = "SELECT tournament_type_name FROM tournament_type WHERE tournament_type_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        if (results.next()) {
            return mapRowToTournamentType(results);
        } else
        return null;
    }


    private TournamentType mapRowToTournamentType(SqlRowSet rs) {
        TournamentType tournamentType = new TournamentType();
        tournamentType.setTournamentTypeId(rs.getInt("tournament_type_id"));
        tournamentType.setTournamentTypeName(rs.getString("tournament_type_name"));
        return tournamentType;
    }


}
