package com.techelevator.model;

public class TournamentUser {

    private int tournamentId;
    private int playerId;
    private String username;
    private boolean isOut;
    private int seed;

    public TournamentUser() {};

    public TournamentUser(int tournamentId, int playerId, boolean isOut, int seed) {
        this.tournamentId = tournamentId;
        this.playerId = playerId;
        this.isOut = isOut;
        this.seed = seed;
    }

    public boolean isOut() {
        return isOut;
    }

    public void setOut(boolean out) {
        isOut = out;
    }

    public int getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(int tournamentId) {
        this.tournamentId = tournamentId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getSeed() {
        return seed;
    }

    public void setSeed(int seed) {
        this.seed = seed;
    }
}
