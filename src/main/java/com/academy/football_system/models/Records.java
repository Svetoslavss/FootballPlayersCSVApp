package com.academy.football_system.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Records implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private int id;
    private int playerId;
    private int matchId;
    private int fromMinutes;
    private Integer toMinutes;
}
