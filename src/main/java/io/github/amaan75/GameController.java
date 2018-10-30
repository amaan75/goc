package io.github.amaan75;

import io.github.amaan75.dao.TeamDao;
import io.github.amaan75.dto.TeamDto;
import io.github.amaan75.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * The Game controller is responsible for controlling the entire game,
 */
public class GameController {

    private String team1Name;
    private String team2Name;
    private int matchCounter = 1;
    private int totalMatchLimit;
    private List<TeamDao> teamDaoList;


    public GameController(int numberOfMatches, String fileName) {
        teamDaoList = initTeams(fileName);
        totalMatchLimit = numberOfMatches;
    }

    public GameController(String team1Name, String team2Name, int numberOfMatches) {
        this.team1Name = team1Name;
        this.team2Name = team2Name;
        totalMatchLimit = numberOfMatches;
    }

    /**
     * The playGame method is used to play the game between  2 teams a specified number
     * of times
     */
    void playGame() {
        for (int i = 0; i < totalMatchLimit; i++) {
            TeamDao team1 = new TeamDao(this.team1Name);
            TeamDao team2 = new TeamDao(this.team2Name);
            Match match = new Match(team1, team2);
            match.playGame(matchCounter++);
            ScoreBoard scoreBoard = new ScoreBoard(match);
            scoreBoard.showScore();
        }
    }


    private List<TeamDao> initTeams(String fileName) {
        List<TeamDao> teamDaoList = new ArrayList<>();
        Utils.teamParser(fileName)
                .forEach(teamDto -> teamDaoList.add(TeamDao.from(teamDto)));
        return teamDaoList;

    }


}