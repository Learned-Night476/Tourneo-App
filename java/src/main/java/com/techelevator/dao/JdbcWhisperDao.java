package com.techelevator.dao;

import com.techelevator.model.TournamentMessage;
import com.techelevator.model.Tournaments;
import com.techelevator.model.Whisper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcWhisperDao implements WhisperDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcWhisperDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void createWhisper(Whisper whisper) {
        String sql = "INSERT INTO whispers (player_id, to_player_id, whisper_message, isRead) values (?, ?, ?, ?)";

        jdbcTemplate.update(sql, whisper.getPlayerId(), whisper.getToPlayerId(), whisper.getWhisperMessage(), whisper.isRead());



    }

    @Override
    public List<Whisper> getWhispersByPlayerId(int id) {
        List<Whisper> whispers = new ArrayList<>();
        String sql = "SELECT * FROM whispers where player_id = ? or to_player_id = ?";

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, id, id);

        while (rs.next()) {
            Whisper whisper = mapRowToWhisper(rs);
            whispers.add(whisper);
        }

        return whispers;


    }

    private Whisper mapRowToWhisper(SqlRowSet rs) {
        Whisper whisper = new Whisper();

        whisper.setWhisperId(rs.getInt("whisper_id"));
        whisper.setPlayerId(rs.getInt("player_id"));
        whisper.setToPlayerId(rs.getInt("to_player_id"));
        whisper.setRead(rs.getBoolean("isRead"));
        whisper.setWhisperMessage(rs.getString("whisper_message"));

        return whisper;

    }
}
