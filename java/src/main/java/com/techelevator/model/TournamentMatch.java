package com.techelevator.model;

public class TournamentMatch {
    private int matchId;
    private int playerId;
    private int round;
    private int winner;
    private int tournamentId;
    private int awayPlayerId;
    private String playerUsername;
    private String awayPlayerUsername;

    public TournamentMatch() {}

    public TournamentMatch(int matchId, int round, int winner, int tournamentId, int playerId, int awayPlayerId, String playerUsername, String awayPlayerUsername) {
        this.matchId = matchId;
        this.playerId = playerId;
        this.round = round;
        this.winner = winner;
        this.tournamentId = tournamentId;
        this.awayPlayerId = awayPlayerId;
        this.playerUsername = playerUsername;
        this.awayPlayerUsername = awayPlayerUsername;
    }

    public String getAwayPlayerUsername() {
        return awayPlayerUsername;
    }

    public void setAwayPlayerUsername(String awayPlayerUsername) {
        this.awayPlayerUsername = awayPlayerUsername;
    }

    public String getPlayerUsername() {
        return playerUsername;
    }

    public void setPlayerUsername(String playerUsername) {
        this.playerUsername = playerUsername;
    }

    public int getAwayPlayerId() {
        return awayPlayerId;
    }

    public void setAwayPlayerId(int awayPlayerId) {
        this.awayPlayerId = awayPlayerId;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int away) {
        this.playerId = away;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(int tournamentId) {
        this.tournamentId = tournamentId;
    }

    public int getWinner() {
        return winner;
    }

    public void setWinner(int winner) {
        this.winner = winner;
    }

}
