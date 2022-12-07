package com.techelevator.dao;

import com.techelevator.model.Player;

import java.util.List;

public interface PlayerDao {
    public Player getPlayer(int userId);

    public List<Player> getAllPlayers();
}
