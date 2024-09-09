package com.academy.football_system.service;

import com.academy.football_system.exception.InvalidCsvFormatException;
import com.academy.football_system.models.*;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Setter
@Service
public class PlayedMatchService implements PlayerMatchesINT {

    private  Map<Integer, Players> players = new HashMap<>();
    private  Map<Integer, Records> records = new HashMap<>();
    private  List<Match> matches = new ArrayList<>();
    private  List<Teams> teams = new ArrayList<>();

    @Override
    public void processLongestPlayed(String filePath){
        Map<Integer, List<Records>> matchRecords = records.values()
                .stream().collect(Collectors.groupingBy(Records::getMatchId));

        Map<Pairs<Integer, Integer>, Integer> playersPairTotalMin = new HashMap<>();

        for (List<Records> record : matchRecords.values()){
            Map<Integer, List<Records>> player = record
                    .stream().collect(Collectors.groupingBy(Records::getPlayerId));

            List<Integer> playerIds = new ArrayList<>(player.keySet());

            for (int i = 0 ; i < playerIds.size() ; i++){
                for (int j = i + 1; j < playerIds.size(); j++) {
                    int player1 = playerIds.get(i);
                    int player2 = playerIds.get(j);

                    List<Records> player1Rec = player.get(player1);
                    List<Records> player2Rec = player.get(player2);

                    int totalMin = calcMin(player1Rec, player2Rec);

                    Pairs<Integer, Integer> playedPair = new Pairs<>(player1, player2);
                    playersPairTotalMin.merge(playedPair, totalMin, Integer::sum);
                }
            }
        }
        Pairs<Integer, Integer> longest = null;
        int maxMin = 0;

        for (Map.Entry<Pairs<Integer, Integer>, Integer> entry : playersPairTotalMin.entrySet()){
            if (entry.getValue() > maxMin){
                maxMin = entry.getValue();
                longest = entry.getKey();
            }
        }

        if (longest != null){
            int player1 = longest.getFirst();
            int player2 = longest.getSecond();

            Players players1 = players.get(player1);
            Players players2 = players.get(player2);

            try (FileWriter writer = new FileWriter(filePath)) {
                writer.append("Player 1, Player 2, Total Minutes\n");
                writer.append(players1.getFullName()).append(",");
                writer.append(players2.getFullName()).append(",");
                writer.append(String.valueOf(maxMin)).append("\n");
            } catch (IOException e){
                throw new InvalidCsvFormatException("Invalid file format.");
            }
        }
    }

        @Override
        public int calcMin(List<Records> player1Rec, List<Records> player2Rec) {
        int total = 0;

        for (Records record : player1Rec){
            for (Records record1 : player2Rec){
                if (record.getMatchId() == record1.getMatchId()){
                    int fromMin = Math.max(record.getFromMinutes(), record1.getFromMinutes());
                    int toMin = Math.min(record.getToMinutes() != null ? record.getToMinutes() : 90 ,
                            record1.getToMinutes() != null ? record1.getToMinutes() : 90);
                    if (fromMin < toMin){
                        total += (toMin - fromMin);
                    }
                }
            }
        }
        return total;

    }

}