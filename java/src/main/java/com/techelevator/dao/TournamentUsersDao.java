package com.techelevator.dao;

import com.techelevator.model.TournamentUser;

import java.util.List;

public interface TournamentUsersDao {

    boolean createTournamentUser(int tournamentId, int playerId, int seed);

    List<TournamentUser> getTournamentUsers(int tournamentId);

}
