package com.techelevator.model;

public class Tournaments {

    private int tournamentId;
    private int participants;
    private int winner;
    private int adminUser;
    private String tournamentStatus;
    private int tournamentTypeId;
    private String tournamentName;

    public Tournaments(int tournamentId, int participants, int winner, int adminUser, String tournamentStatus, int tournamentTypeId, String tournamentName) {
        this.tournamentId = tournamentId;
        this.participants = participants;
        this.winner = winner;
        this.adminUser = adminUser;
        this.tournamentStatus = tournamentStatus;
        this.tournamentTypeId = tournamentTypeId;
        this.tournamentName = tournamentName;
    }

    public Tournaments() {};

    public int getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(int tournamentId) {
        this.tournamentId = tournamentId;
    }

    public int getParticipants() {
        return participants;
    }

    public void setParticipants(int participants) {
        this.participants = participants;
    }

    public int getWinner() {
        return winner;
    }

    public void setWinner(int winner) {
        this.winner = winner;
    }

    public int getAdminUser() {
        return adminUser;
    }

    public void setAdminUser(int adminUser) {
        this.adminUser = adminUser;
    }

    public String getTournamentStatus() {
        return tournamentStatus;
    }

    public void setTournamentStatus(String tournamentStatus) {
        this.tournamentStatus = tournamentStatus;
    }

    public int getTournamentTypeId() {
        return tournamentTypeId;
    }

    public void setTournamentTypeId(int tournamentTypeId) {
        this.tournamentTypeId = tournamentTypeId;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }
}
