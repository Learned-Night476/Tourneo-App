package com.techelevator.model;

public class TournamentMatch {
    private int matchId;
    private int homeId;
    private int awayId;
    private int round;
    private int winner;
    private int tournamentId;

    public TournamentMatch() {};

    public TournamentMatch(int matchId, int homeId, int awayId, int round, int winner, int tournamentId) {
        this.matchId = matchId;
        this.homeId = homeId;
        this.awayId = awayId;
        this.round = round;
        this.winner = winner;
        this.tournamentId = tournamentId;
    }

    public int getHomeId() {
        return homeId;
    }

    public void setHomeId(int homeId) {
        this.homeId = homeId;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getAwayId() {
        return awayId;
    }

    public void setAwayId(int awayId) {
        this.awayId = awayId;
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
