package com.techelevator.dao;

import com.techelevator.model.TournamentType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

public interface TournamentTypeDao {

    TournamentType getTournamentTypeDao(int id);

}
