package com.techelevator.model;

public class TournamentMessage {

    private int tournamentMessageId;
    private int adminUser;
    private int tournamentId;
    private String senderUsername;
    private String matchDescription;
    private String winner;
    private boolean unread;

    public TournamentMessage() {};

    public TournamentMessage(int tournamentMessageId, int adminUser, int tournamentId, String senderUsername, String tournamentDescription, String winner, boolean unread) {
        this.tournamentMessageId = tournamentMessageId;
        this.adminUser = adminUser;
        this.tournamentId = tournamentId;
        this.senderUsername = senderUsername;
        this.matchDescription = tournamentDescription;
        this.winner = winner;
        this.unread = unread;
    }

    public boolean isUnread() {
        return unread;
    }

    public void setUnread(boolean unread) {
        this.unread = unread;
    }

    public int getTournamentMessageId() {
        return tournamentMessageId;
    }

    public void setTournamentMessageId(int tournamentMessageId) {
        this.tournamentMessageId = tournamentMessageId;
    }

    public int getAdminUser() {
        return adminUser;
    }

    public void setAdminUser(int adminUser) {
        this.adminUser = adminUser;
    }

    public int getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(int tournamentId) {
        this.tournamentId = tournamentId;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public String getMatchDescription() {
        return matchDescription;
    }

    public void setMatchDescription(String tournamentDescription) {
        this.matchDescription = tournamentDescription;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
}
