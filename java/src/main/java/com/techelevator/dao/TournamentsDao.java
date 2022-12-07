package com.techelevator.dao;

import com.techelevator.model.Tournaments;

public interface TournamentsDao {
    Tournaments getTournament(int id);

     Tournaments createTournament(Tournaments tournament);
}
