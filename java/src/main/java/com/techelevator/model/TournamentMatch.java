package com.techelevator.model;

public class TournamentMatch {
    private int matchId;
    private int playerId;
    private int round;
    private int winner;
    private int tournamentId;
    private int awayPlayerId;

    public TournamentMatch() {}

    public TournamentMatch(int matchId, int round, int winner, int tournamentId, int playerId, int awayPlayerId) {
        this.matchId = matchId;
        this.playerId = playerId;
        this.round = round;
        this.winner = winner;
        this.tournamentId = tournamentId;
        this.awayPlayerId = awayPlayerId;
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
