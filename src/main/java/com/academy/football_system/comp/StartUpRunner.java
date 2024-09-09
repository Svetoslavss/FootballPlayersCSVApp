package com.academy.football_system.comp;

import com.academy.football_system.models.Match;
import com.academy.football_system.models.Players;
import com.academy.football_system.models.Records;
import com.academy.football_system.models.Teams;
import com.academy.football_system.repository.JdbcRepository;
import com.academy.football_system.service.PlayedMatchService;
import com.academy.football_system.utils.CSVUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.List;
import java.util.Map;

public class StartUpRunner implements CommandLineRunner {

    @Autowired
    private PlayedMatchService service;

    @Autowired
    private JdbcRepository repository;

    @Override
    public void run(String... args) throws Exception {

        repository.create();

        Map<Integer, Players> players = CSVUtil.readPlayers("players.csv");
        Map<Integer, Records> records = CSVUtil.readRecords("records.csv");
        List<Match> matches = CSVUtil.readMatches("matches.csv");
        List<Teams> teams = CSVUtil.readTeams("teams.csv");
        service.setPlayers(players);
        service.setRecords(records);
        service.setMatches(matches);
        service.setTeams(teams);
        service.processLongestPlayed("players_pair_matches.csv");
    }
}
