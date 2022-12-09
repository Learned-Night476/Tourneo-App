package com.techelevator.dao;

import com.techelevator.model.TournamentMatch;

import java.util.List;

public interface TournamentMatchDao {

    void createTournamentMatch(TournamentMatch tournamentMatch);

    List<TournamentMatch> getTournamentMatchsByTournamentIdAndRound(int tournamentId, int round);

}
