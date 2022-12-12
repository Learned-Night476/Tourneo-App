package com.techelevator.dao;

import com.techelevator.model.Whisper;

import java.util.List;

public interface WhisperDao {

    void createWhisper(Whisper whisper);

    List<Whisper> getWhispersByPlayerId(int id);

}
