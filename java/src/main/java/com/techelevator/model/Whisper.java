package com.techelevator.model;

public class Whisper {
    private int whisperId;
    private int playerId;
    private int toPlayerId;
    private String whisperMessage;
    private boolean isRead;

    public Whisper() {};

    public Whisper(int whisperId, int playerId, int toPlayerId, String whisperMessage, boolean isRead) {
        this.whisperId = whisperId;
        this.playerId = playerId;
        this.toPlayerId = toPlayerId;
        this.whisperMessage = whisperMessage;
        this.isRead = isRead;
    }

    public int getWhisperId() {
        return whisperId;
    }

    public void setWhisperId(int whisperId) {
        this.whisperId = whisperId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getToPlayerId() {
        return toPlayerId;
    }

    public void setToPlayerId(int toPlayerId) {
        this.toPlayerId = toPlayerId;
    }

    public String getWhisperMessage() {
        return whisperMessage;
    }

    public void setWhisperMessage(String whisperMessage) {
        this.whisperMessage = whisperMessage;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean isRead) {
        this.isRead = isRead;
    }
}
