package com.techelevator.dao;

import com.techelevator.model.TournamentMatch;

public interface TournamentMatchDao {

    boolean createTournamentMatch(int tournamentId, int homePlayerId, int awayPlayerId, int round);

}
