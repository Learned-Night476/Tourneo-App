package com.techelevator.model;

public class TournamentUser {

    private int tournamentId;
    private int playerId;

    public TournamentUser() {};

    public TournamentUser(int tournamentId, int playerId) {
        this.tournamentId = tournamentId;
        this.playerId = playerId;
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
}
