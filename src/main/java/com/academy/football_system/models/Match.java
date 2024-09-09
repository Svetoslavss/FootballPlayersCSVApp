package com.academy.football_system.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Match implements Serializable {

    private static final long serialVersionUID = 1L;
    private int matchId;
    private int teamAId;
    private int teamBId;
    private String startTime;
    private String score;

}


