package io.github.amaan75.match;

import io.github.amaan75.MatchUtils;
import io.github.amaan75.ScoreBoard;
import io.github.amaan75.model.TeamModel;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for Handling a single match between two teams
 */
public class Match implements MatchLifecycle {


    private TeamModel team1;
    private TeamModel team2;
    private List<MatchLifeCycleCallBackListener> matchLifeCycleCallBackListenerList;


    public Match(TeamModel team1, TeamModel team2) {
        this.team1 = team1;
        this.team2 = team2;
    }


    // this method registers the callback listener
    // gamesController in our case
    public void registerCallBackListener(MatchLifeCycleCallBackListener matchLifeCycleCallBackListener) {
        if (matchLifeCycleCallBackListenerList == null)
            matchLifeCycleCallBackListenerList = new ArrayList<>();
        matchLifeCycleCallBackListenerList.add(matchLifeCycleCallBackListener);
    }


    @Override
    public void startGame() {
        matchLifeCycleCallBackListenerList.forEach(MatchLifeCycleCallBackListener::startGameCallback);
        team1.finaliseTeamForNewGame();
        team2.finaliseTeamForNewGame();
        playGame();
    }

    @Override
    public void playGame() {
        matchLifeCycleCallBackListenerList.forEach(MatchLifeCycleCallBackListener::playGameCallback);
        MatchUtils.startInning(team1, -1, (ScoreBoard) matchLifeCycleCallBackListenerList.get(1));
        MatchUtils.endInningAndReportStats(team1);
        MatchUtils.startInning(team2, team1.getRuns(), (ScoreBoard) matchLifeCycleCallBackListenerList.get(1));
        MatchUtils.endInningAndReportStats(team2);
        endGame(MatchUtils.computeAndDeclareWinner(team1, team2));
    }

    @Override
    public void endGame(TeamModel team) {
        matchLifeCycleCallBackListenerList.forEach(matchLifeCycleCallBackListener ->
                matchLifeCycleCallBackListener.endGameCallback(team));
    }
}
