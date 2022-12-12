package com.techelevator.dao;

import com.techelevator.model.TournamentMessage;
import com.techelevator.model.Tournaments;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTournamentMessageDao implements TournamentMessageDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTournamentMessageDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createTournamentMessage(TournamentMessage tournamentMessage) {
        String sql = "INSERT INTO tournament_messages (admin_user, tournament_id, sender_username, match_description, winner, unread) values (?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, tournamentMessage.getAdminUser(), tournamentMessage.getTournamentId(), tournamentMessage.getSenderUsername(), tournamentMessage.getMatchDescription(), tournamentMessage.getWinner(), tournamentMessage.isUnread());

    }

    @Override
    public List<TournamentMessage> getTournamentMessagesByTournamentId(int id) {
        List<TournamentMessage> tournamentMessages = new ArrayList<>();

        String sql = "select tournament_message_id, admin_user, tournament_id, sender_username, match_description, winner, unread from tournament_messages WHERE tournament_id = ?";

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, id);

        while (rs.next()) {
            TournamentMessage tournamentMessage = mapRowToTournamentMessage(rs);
            tournamentMessages.add(tournamentMessage);
        }

        return tournamentMessages;

    }

    @Override
    public TournamentMessage getMessageByMessageId(int id) {
        String sql = "select tournament_message_id, admin_user, tournament_id, sender_username, match_description, winner, unread from tournament_messages WHERE tournament_message_id = ?";


        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        if (results.next()) {
            return mapRowToTournamentMessage(results);
        } else {
            return null;
        }
    }

    private TournamentMessage mapRowToTournamentMessage(SqlRowSet rs) {
        TournamentMessage tournamentMessage = new TournamentMessage();

        tournamentMessage.setAdminUser(rs.getInt("admin_user"));
        tournamentMessage.setSenderUsername(rs.getString("sender_username"));
        tournamentMessage.setMatchDescription(rs.getString("match_description"));
        tournamentMessage.setTournamentMessageId(rs.getInt("tournament_message_id"));
        tournamentMessage.setTournamentId(rs.getInt("tournament_id"));
        tournamentMessage.setUnread(rs.getBoolean("unread"));
        tournamentMessage.setWinner(rs.getString("winner"));

        return tournamentMessage;

    }
}
