package io.github.amaan75;

import io.github.amaan75.match.Match;
import io.github.amaan75.match.MatchLifeCycleCallBackListener;
import io.github.amaan75.model.TeamModel;
import io.github.amaan75.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * The Games controller controls all the Matches in match.
 */
public class GamesController implements MatchLifeCycleCallBackListener {


    private int matchCounter;
    private int totalMatchLimit;
    private List<TeamModel> teamModelList;


    public GamesController(int numberOfMatches, String fileName) {
        teamModelList = initTeams(fileName);
        totalMatchLimit = numberOfMatches;
    }


    /**
     * The playGame method is used to play the game between  2 teams a specified number
     * of times
     */
    void playGame() {
        for (matchCounter = 1; matchCounter < totalMatchLimit; matchCounter++) {
            Match match = new Match(teamModelList.get(0), teamModelList.get(1));
            match.registerCallBackListener(this, new ScoreBoard(teamModelList.get(0), teamModelList.get(0)));
            match.startGame();
        }
    }

    @Override
    public void startGameCallback() {
        MatchUtils.printStartMatchMessage(matchCounter);

    }

    @Override
    public void playGameCallback() {

    }

    @Override
    public void endGameCallback(TeamModel teamModel) {
        MatchUtils.printEndMatchMessage(matchCounter);

    }


    private List<TeamModel> initTeams(String fileName) {
        List<TeamModel> teamModelList = new ArrayList<>();
        Utils.teamParser(fileName)
                .forEach(teamDto -> teamModelList.add(TeamModel.from(teamDto)));
        return teamModelList;
    }

}