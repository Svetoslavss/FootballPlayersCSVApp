package com.academy.football_system.repository;

import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class JdbcRepository {

    private String url = "jdbc:postgresql://localhost:5432/footballdb";
    private String username = "postgres";
    private String password = "password";

    public void create() {

        String teamsSLQ = "CREATE TABLE IF NOT EXISTS teams ("
                + "teamId SERIAL PRIMARY KEY, "
                + "managerName VARCHAR(255) NOT NULL, "
                + "team_group VARCHAR(255) NOT NULL"
                + ")";

        String playerSQL = "CREATE TABLE IF NOT EXISTS players ("
                + "id SERIAL PRIMARY KEY, "
                + "teamNumber INT NOT NULL, "
                + "position VARCHAR(255) NOT NULL, "
                + "fullName VARCHAR (255) NOT NULL, "
                + "teamId INT, "
                + "FOREIGN KEY (teamId) REFERENCES teams(teamId)"
                + ")";

       try (Connection con = DriverManager.getConnection(url, username, password);
           Statement stm = con.createStatement()){
           stm.executeUpdate(playerSQL);
           stm.executeUpdate(teamsSLQ);
       } catch (SQLException e){
           e.printStackTrace();
       }

    }
}
