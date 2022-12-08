package com.techelevator.model;

public class TournamentMatch {
    private int matchId;
    private int home;
    private int away;
    private int round;
    private int winner;
    private int tournamentId;

    public TournamentMatch() {};

    public TournamentMatch(int matchId, int home, int away, int round, int winner, int tournamentId) {
        this.matchId = matchId;
        this.home = home;
        this.away = away;
        this.round = round;
        this.winner = winner;
        this.tournamentId = tournamentId;
    }

    public int getHomeId() {
        return home;
    }

    public void setHomeId(int home) {
        this.home = home;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getAwayId() {
        return away;
    }

    public void setAwayId(int away) {
        this.away = away;
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
