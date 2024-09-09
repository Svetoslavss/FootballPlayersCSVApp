package com.academy.football_system.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teams implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private int teamId;
    private String name;
    private String managerName;
    private String teamGroup;
}
