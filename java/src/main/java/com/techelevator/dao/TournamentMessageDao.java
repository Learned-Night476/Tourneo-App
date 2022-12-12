package com.techelevator.dao;

import com.techelevator.model.TournamentMessage;

import java.util.List;

public interface TournamentMessageDao {

    void createTournamentMessage(TournamentMessage tournamentMessage);

    List<TournamentMessage> getTournamentMessagesByTournamentId(int id);

    TournamentMessage getMessageByMessageId(int id);
}
