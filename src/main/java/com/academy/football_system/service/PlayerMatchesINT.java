package com.academy.football_system.service;

import com.academy.football_system.models.Records;

import java.util.List;

public interface PlayerMatchesINT {

    void processLongestPlayed(String filePath);
    int calcMin(List<Records> player, List<Records> player1);
}
