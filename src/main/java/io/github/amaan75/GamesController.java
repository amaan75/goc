package io.github.amaan75;

import io.github.amaan75.match.CricketMatch;
import io.github.amaan75.match.MatchCallBackListener;
import io.github.amaan75.model.Team;
import io.github.amaan75.utils.Utils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The Games controller controls all the Matches in the game.
 */
public class GamesController implements MatchCallBackListener {


    private int matchCounter;
    private int totalMatchLimit;
    private List<Team> teamModelList;


    public GamesController(int numberOfMatches, String fileName) {
        teamModelList = initTeams(fileName);
        totalMatchLimit = numberOfMatches;
    }


    /**
     * The playGame method is used to play the game between  2 teams a specified number
     * of times
     */
    public void playGame() {
        for (matchCounter = 1; matchCounter <= totalMatchLimit; matchCounter++) {
            ScoreBoard scoreBoard = new ScoreBoard();
            CricketMatch match = new CricketMatch(teamModelList.get(0), teamModelList.get(1));
            match.registerCallBackListener(List.of(
                    this,
                    scoreBoard
            ));
            match.registerBallEventListeners(scoreBoard);
            match.setScoreBoard(scoreBoard);
            match.startGame();
        }
    }

    @Override
    public void matchStarted(LocalDateTime now, Team team1, Team team2) {
        Utils.printMessage(
                String.format("*******************************BEGIN MATCH %d*****************************", matchCounter)
        );
        Utils.printMessage(
                String.format(" match %d began held on %s between teams %s and %s",
                        matchCounter, now.toLocalDate(), team1.getTeamName(), team2.getTeamName()));
    }

    @Override
    public void matchFinished(Team team1, Team team2) {
        MatchUtils.printEndMatchMessage(matchCounter);
    }


    private List<Team> initTeams(String fileName) {
        List<Team> teamModelList = new ArrayList<>();
        Utils.teamParser(fileName)
                .forEach(teamDto -> teamModelList.add(Team.from(teamDto)));
        return teamModelList;
    }

}