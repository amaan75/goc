package io.github.amaan75;

import io.github.amaan75.dao.TeamDao;
import io.github.amaan75.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * The Game controller is responsible for controlling the entire game,
 */
public class GameController {


    private int matchCounter = 1;
    private int totalMatchLimit;
    private List<TeamDao> teamDaoList;


    public GameController(int numberOfMatches, String fileName) {
        teamDaoList = initTeams(fileName);
        totalMatchLimit = numberOfMatches;
    }


    /**
     * The playGame method is used to play the game between  2 teams a specified number
     * of times
     */
    void playGame() {
        for (int i = 0; i < totalMatchLimit; i++) {
            Match match = new Match(teamDaoList.get(0), teamDaoList.get(1));
            match.playGame(matchCounter++);
        }
    }


    private List<TeamDao> initTeams(String fileName) {
        List<TeamDao> teamDaoList = new ArrayList<>();
        Utils.teamParser(fileName)
                .forEach(teamDto -> teamDaoList.add(TeamDao.from(teamDto)));
        return teamDaoList;
    }

}