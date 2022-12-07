package com.techelevator.model;

public class TournamentType {
    private int tournamentTypeId;
    private String tournamentTypeName;


    public TournamentType() {}

    public TournamentType(int tournamentTypeId, String tournamentTypeName) {
        this.tournamentTypeId = tournamentTypeId;
        this.tournamentTypeName = tournamentTypeName;
    }

    public int getTournamentTypeId() {
        return tournamentTypeId;
    }

    public void setTournamentTypeId(int tournamentTypeId) {
        this.tournamentTypeId = tournamentTypeId;
    }

    public String getTournamentTypeName() {
        return tournamentTypeName;
    }

    public void setTournamentTypeName(String tournamentTypeName) {
        this.tournamentTypeName = tournamentTypeName;
    }

}
