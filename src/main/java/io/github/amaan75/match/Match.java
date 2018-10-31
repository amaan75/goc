package io.github.amaan75.match;

import io.github.amaan75.MatchUtils;
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
    public void registerCallBackListener(List<MatchLifeCycleCallBackListener> matchLifeCycleCallBackListenerList) {
        if (this.matchLifeCycleCallBackListenerList == null)
            this.matchLifeCycleCallBackListenerList = new ArrayList<>();
        this.matchLifeCycleCallBackListenerList.addAll(matchLifeCycleCallBackListenerList);
    }


    public void registerCallBackListener(MatchLifeCycleCallBackListener matchLifeCycleCallBackListener) {
        if (matchLifeCycleCallBackListenerList == null)
            matchLifeCycleCallBackListenerList = new ArrayList<>();
        matchLifeCycleCallBackListenerList.add(matchLifeCycleCallBackListener);
    }

    /**
     * part of match lifecycle -start game when the match starts
     */
    @Override
    public void startGame() {
        matchLifeCycleCallBackListenerList
                .forEach(MatchLifeCycleCallBackListener::startGameCallback);
        team1.finaliseTeamForNewGame();
        team2.finaliseTeamForNewGame();
        playGame();
    }

    /**
     * executes right after startGame, actual game play happens here
     */
    @Override
    public void playGame() {
        matchLifeCycleCallBackListenerList
                .forEach(MatchLifeCycleCallBackListener::playGameCallback);
        MatchUtils.startInning(team1, -1);
        MatchUtils.endInningAndReportStats(team1);
        MatchUtils.startInning(team2, team1.getRuns());
        MatchUtils.endInningAndReportStats(team2);
        endGame(team1, team2);
    }

    /**
     * This method is called the match finishes
     *
     * @param team1 final state of team 1 after the match
     * @param team2 final state of team 2 after the match
     */
    @Override
    public void endGame(TeamModel team1, TeamModel team2) {
        matchLifeCycleCallBackListenerList
                .forEach(matchLifeCycleCallBackListener ->
                        matchLifeCycleCallBackListener.endGameCallback(team1, team2));
    }
}
