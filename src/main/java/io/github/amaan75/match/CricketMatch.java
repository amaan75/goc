package io.github.amaan75.match;

import io.github.amaan75.MatchUtils;
import io.github.amaan75.ScoreBoard;
import io.github.amaan75.model.Team;
import io.github.amaan75.model.TeamScore;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for Handling a single Match between two teams
 */
public class CricketMatch implements Match {
    private static final int TOTAL_BALLS = 120;

    private Team team1;
    private Team team2;
    private ScoreBoard scoreBoard;

    public ScoreBoard getScoreBoard() {
        return scoreBoard;
    }

    public void setScoreBoard(ScoreBoard scoreBoard) {
        this.scoreBoard = scoreBoard;
    }

    private List<MatchCallBackListener> matchCallBackListenerList;
    private List<BallEventListener> ballEventListenerList;


    public CricketMatch(Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
    }


    // this method registers the callback listener
    public void registerCallBackListener(List<MatchCallBackListener> matchCallBackListenerList) {
        if (this.matchCallBackListenerList == null)
            this.matchCallBackListenerList = new ArrayList<>();
        this.matchCallBackListenerList.addAll(matchCallBackListenerList);
    }


    public void registerCallBackListener(MatchCallBackListener matchCallBackListener) {
        if (matchCallBackListenerList == null)
            matchCallBackListenerList = new ArrayList<>();
        matchCallBackListenerList.add(matchCallBackListener);
    }


    public void registerBallEventListeners(BallEventListener ballEventListener) {
        if (ballEventListenerList == null)
            ballEventListenerList = new ArrayList<>();
        ballEventListenerList.add(ballEventListener);

    }

    /**
     * part of Match lifecycle -start game when the Match starts
     */
    @Override
    public void startGame() {
        matchCallBackListenerList
                .forEach(matchCallBackListener ->
                        matchCallBackListener.matchStarted(
                                LocalDateTime.now(), team1, team2
                        )
                );
        playGame();
    }

    /**
     * executes right after startGame, actual game play happens here
     */
    @Override
    public void playGame() {
        TeamScore team1Score = scoreBoard.getTeamScoreHashMap().get(team1);
        TeamScore team2Score = scoreBoard.getTeamScoreHashMap().get(team2);
        playInning(team1, Integer.MAX_VALUE, team1Score);
        MatchUtils.endInningAndReportStats(team1, team1Score);
        playInning(team2, team1Score.getTeamRuns(), team2Score);
        MatchUtils.endInningAndReportStats(team2, team2Score);
        endGame(team1, team2);
    }


    /**
     * OverLoaded method for the inning and this method will also take a 3rd param, the target runs
     * That the playing team has to beat in order to win.
     *
     * @param team       this is the team that currently batting.
     * @param targetRuns these are the target runs, that the current batting team has to beat.
     */
    private void playInning(Team team, int targetRuns, TeamScore teamScore) {
        while (!teamScore.isTeamOut() &&
                teamScore.getBallsUsed() < TOTAL_BALLS &&
                teamScore.getTeamRuns() <= targetRuns) {
            int ballResult = MatchUtils.playBall(team
                    .getPlayerFromList(teamScore.getCurrentPlayerNumber())
                    .getPlayerRole());
            ballEventListenerList.forEach(ballEventListener ->
                    ballEventListener.ballEvent(ballResult, team));
        }


    }


    /**
     * This method is called the Match finishes
     *
     * @param team1 final state of team 1 after the Match
     * @param team2 final state of team 2 after the Match
     */
    @Override
    public void endGame(Team team1, Team team2) {
        matchCallBackListenerList
                .forEach(matchCallBackListener ->
                        matchCallBackListener.matchFinished(team1, team2));
    }
}
