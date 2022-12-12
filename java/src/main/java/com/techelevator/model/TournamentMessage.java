package com.techelevator.model;

public class TournamentMessage {

    private int tournamentMessageId;
    private int adminUser;
    private int tournamentId;
    private String senderUsername;
    private String tournamentDescription;
    private String winner;
    private boolean unread;

    public TournamentMessage() {};

    public TournamentMessage(int tournamentMessageId, int adminUser, int tournamentId, String senderUsername, String tournamentDescription, String winner) {
        this.tournamentMessageId = tournamentMessageId;
        this.adminUser = adminUser;
        this.tournamentId = tournamentId;
        this.senderUsername = senderUsername;
        this.tournamentDescription = tournamentDescription;
        this.winner = winner;
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

    public String getTournamentDescription() {
        return tournamentDescription;
    }

    public void setTournamentDescription(String tournamentDescription) {
        this.tournamentDescription = tournamentDescription;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
}
