package com.academy.football_system.utils;

import com.academy.football_system.exception.InvalidCsvFormatException;
import com.academy.football_system.models.Match;
import com.academy.football_system.models.Players;
import com.academy.football_system.models.Records;
import com.academy.football_system.models.Teams;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVUtil {

    public static Map<Integer, Players> readPlayers(String filePath) {
        Map<Integer, Players> players = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                line = line.replace("\ufeff", "");
                String[] data = line.split(",");
                if (data.length < 5) {
                    continue;
                }

                int id = Integer.parseInt(data[0]);
                int teamNumber = Integer.parseInt(data[1]);
                String position = data[2];
                String fullName = data[3];
                int teamId = Integer.parseInt(data[4]);

                Players player = new Players(id, teamNumber, position, fullName, teamId);
                players.put(id, player);
            }
        } catch (IOException e) {
            throw new InvalidCsvFormatException("Invalid players CSV format");
        }
        return players;
    }

    public static Map<Integer, Records> readRecords(String filePath) {
        Map<Integer, Records> record = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isLine = true;

            while ((line = br.readLine()) != null) {

                if (isLine) {
                    isLine = false;
                    continue;
                }
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                int playerId = Integer.parseInt(data[1]);
                int matchId = Integer.parseInt(data[2]);
                int fromMin = Integer.parseInt(data[3]);
                Integer toMin = null;
                if (!data[4].equals("NULL") && !data[4].trim().isEmpty()) {
                    toMin = Integer.parseInt(data[4]);
                }
                Records records = new Records(id, playerId, matchId, fromMin, toMin);
                record.put(id, records);
            }
        } catch (IOException e) {
            throw new InvalidCsvFormatException("Invalid CSV format");
        } catch (NumberFormatException e) {
            throw new InvalidCsvFormatException("Invalid number format in CSV: " + e.getMessage());
        }
        return record;
    }

    public static List<Match> readMatches(String filePath) {
        List<Match> matches = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                int teamAId = Integer.parseInt(data[1]);
                int teamBId = Integer.parseInt(data[2]);
                String date = data[3];
                String score = data[4];

                matches.add(new Match(id, teamAId, teamBId, date, score));
            }
        } catch (IOException e) {
            throw new InvalidCsvFormatException("Invalid matches CSV format");
        }
        return matches;
    }


    public static List<Teams> readTeams(String filePath) {
        List<Teams> teams = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] data = line.split(",");
                int teamId = Integer.parseInt(data[0]);
                String name = data[1];
                String managerName = data[2];
                String group = data[3];

                teams.add(new Teams(teamId, name, managerName, group));
            }
        } catch (IOException e) {
            throw new InvalidCsvFormatException("Invalid teams CSV format");
        }
        return teams;
    }
}
