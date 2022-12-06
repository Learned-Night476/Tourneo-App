package com.techelevator.model;

public class Player {
    private int playerId;
    private int wins;
    private int losses;
    private int userId;

    public Player(int playerId, int wins, int losses, int userId) {
        this.playerId = playerId;
        this.wins = wins;
        this.losses = losses;
        this.userId = userId;
    }

    public Player() {}

    public int getPlayerId() {
        return playerId;
    }

    public int getLosses() {
        return losses;
    }

    public int getUserId() {
        return userId;
    }

    public int getWins() {
        return wins;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }
}
