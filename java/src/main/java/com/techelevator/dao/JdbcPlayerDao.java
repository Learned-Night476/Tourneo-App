package com.techelevator.dao;

import com.techelevator.model.Player;
import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class JdbcPlayerDao implements PlayerDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcPlayerDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Player getPlayer(int userId) {
        String sql = "select player_id, wins, losses, user_id from players where user_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        Player player = new Player();
        if (results.next()) {
           player = mapRowToPlayer(results);
        }
        return player;

    }

    private Player mapRowToPlayer(SqlRowSet rs) {


        Player player = new Player();
        player.setPlayerId(rs.getInt("player_id"));
        player.setLosses(rs.getInt("losses"));
        player.setWins(rs.getInt("wins"));
        player.setUserId(rs.getInt("user_id"));

        return player;


    }
}
