package com.techelevator.dao;

import com.techelevator.model.Player;

import java.util.List;

public interface PlayerDao {
    public Player getPlayer(int userId);

    public List<Player> getAllPlayers();

    Player getPlayerByUsername(String username);

    int getPlayerIdByUsername(String username);

}
