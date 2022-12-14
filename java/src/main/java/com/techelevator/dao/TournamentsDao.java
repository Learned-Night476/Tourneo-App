package com.techelevator.dao;

import com.techelevator.model.Tournaments;

import java.util.List;

public interface TournamentsDao {
     Tournaments getTournament(int id);

     boolean createTournament(Tournaments tournament);

     List<Tournaments> getAllTournaments();

     List<Tournaments> getTournamentsByAdmin(int id);

     List<Tournaments> getTournamentsByUserId(int id);

     Integer markTournamentCompleted(int tournamentId);


}
