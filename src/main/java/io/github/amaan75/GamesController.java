package io.github.amaan75;

import io.github.amaan75.model.TeamModel;
import io.github.amaan75.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * The Game controller is responsible for controlling the entire game,
 */
public class GameController {


    private int matchCounter = 1;
    private int totalMatchLimit;
    private List<TeamModel> teamModelList;


    public GameController(int numberOfMatches, String fileName) {
        teamModelList = initTeams(fileName);
        totalMatchLimit = numberOfMatches;
    }


    /**
     * The playGame method is used to play the game between  2 teams a specified number
     * of times
     */
    void playGame() {
        for (int i = 0; i < totalMatchLimit; i++) {
            Match match = new Match(teamModelList.get(0), teamModelList.get(1));
            match.playGame(matchCounter++);
        }
    }


    private List<TeamModel> initTeams(String fileName) {
        List<TeamModel> teamModelList = new ArrayList<>();
        Utils.teamParser(fileName)
                .forEach(teamDto -> teamModelList.add(TeamModel.from(teamDto)));
        return teamModelList;
    }

}