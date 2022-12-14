package com.techelevator.dao;

import com.techelevator.model.TournamentUser;

import java.util.List;

public interface TournamentUsersDao {

    boolean createTournamentUser(TournamentUser tournamentUser);

    List<TournamentUser> getTournamentUsers(int tournamentId);

}
