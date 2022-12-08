package com.techelevator.dao;

import com.techelevator.model.Player;
import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class JdbcPlayerDao implements PlayerDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcPlayerDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Player getPlayer(int userId) {
        String sql = "select player_id, wins, losses, user_id, username from players where user_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        Player player = new Player();
        if (results.next()) {
           player = mapRowToPlayer(results);
        }
        return player;

    }

    @Override
    public List<Player> getAllPlayers() {

        List<Player> players = new ArrayList<>();

        String sql = "select player_id, wins, losses, user_id, username from players";

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql);

        while (rs.next()) {
            Player player = mapRowToPlayer(rs);
            players.add(player);
        }
        return players;
    }

    @Override
    public Player getPlayerByUsername(String username) {
        String sql = "select * from players where username = ?";
        Player player = new Player();

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql);

        if (rs.next()) {
            player = mapRowToPlayer(rs);
        }

        return player;
    }

    private Player mapRowToPlayer(SqlRowSet rs) {


        Player player = new Player();
        player.setPlayerId(rs.getInt("player_id"));
        player.setLosses(rs.getInt("losses"));
        player.setWins(rs.getInt("wins"));
        player.setUserId(rs.getInt("user_id"));
        player.setUsername(rs.getString("username"));

        return player;


    }
}
