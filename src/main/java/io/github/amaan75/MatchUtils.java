package io.github.amaan75;

import io.github.amaan75.model.TeamModel;
import io.github.amaan75.utils.Utils;
import org.jetbrains.annotations.NotNull;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Class with only static methods which represent the operations in a single match
 */
class MatchOps {

    //this field will hold the index of last player number who will bat,
    //since a player can't bat alone in cricket
    private final static int LAST_PLAYER = 10;
    //this is the total number of balls in a match
    private static final int TOTAL_BALLS = 20 * 6;

    //cached because this will be used very often
    private static Random rnd = ThreadLocalRandom.current();

    private MatchOps() {
        //do not instantiate
        throw new AssertionError("This class is only for static methods");
    }

    /**
     * @return returns the runs from 0-7 where 7 stands for a wicket
     */
    static int playBall() {
        //bound = 8 because it is exclusive
        return rnd.nextInt(8);
    }

    static void declareAndSetPlayerOut(@NotNull TeamModel team) {
        int currPlayerIndex = team.getCurrentPlayer();
        team.getCurrentPlayerFromList(currPlayerIndex).setPlayerOut();
        Utils.printMessage(String.format("PLAYER %s IS OUT!",
                team.getCurrentPlayerFromList(currPlayerIndex).getPlayerName()));
    }


    /**
     * This method takes two teams, compares the runs and declares the
     * winner
     *
     * @param team1 1st out of the two playing teams
     * @param team2 2nd out of the two playing teams
     */
    static void computeAndDeclareWinner(@NotNull TeamModel team1, @NotNull TeamModel team2) {
        int run1 = team1.getRuns();
        int run2 = team2.getRuns();
        if (run1 > run2)
            Utils.printMessage(String.format("%s wins by %d runs", team1.getTeamName(), (run1 - run2)));
        else if (run1 == run2)
            Utils.printMessage("This match was a draw");
        else {
            Utils.printMessage(String.format("%s won by %d wickets", team2.getTeamName(), team2.getPlayerRemainingCount()));
        }
    }

    /**
     * This method prints a begin match method
     *
     * @param currentMatchCount this is the current Match Count
     */
    static void printStartMatchMessage(int currentMatchCount) {
        Utils.printMessage(String.format(
                "**********************************BEGIN MATCH %d*************************** %n",
                currentMatchCount));
    }


    static void startInning(@NotNull TeamModel team, int teamRuns, ScoreBoard scoreBoard) {
        if (teamRuns < 0)
            playInning(team, scoreBoard);
        else
            playInning(team, teamRuns + 1, scoreBoard);
    }


    /**
     * This method is the implementation for FirstInning, and takes only two parameters because
     * we don't have any target for the first Innings
     *
     * @param team this method is the implementation for the first Inning.
     */
    static void playInning(TeamModel team, ScoreBoard scoreBoard) {
        playInning(team, Integer.MAX_VALUE, scoreBoard);
    }

    /**
     * OverLoaded method for the inning and this method will also take a 3rd param, the target runs
     * That the playing team has to beat in order to win.
     *
     * @param team       this is the team that currently batting.
     * @param targetRuns these are the target runs, that the current batting team has to beat.
     */
    static void playInning(TeamModel team, int targetRuns, ScoreBoard scoreBoard) {
        while (!team.isTeamOut() &&
                team.getBallsUsed() < TOTAL_BALLS &&
                team.getRuns() < targetRuns) {
            //this ball result will store the value that the ball returns either.
            //0-6 or 7 for bold
            int ballResult = MatchOps.playBall();
            if (ballResult <= 6) {
                team.addRun(ballResult);
            } else {
                MatchOps.declareAndSetPlayerOut(team);
                team.callNextPlayer();
                MatchOps.checkAndSetTeamOut(team);
            }
            team.increaseBallUsedCount();
            Utils.printMessage(scoreBoard.computeAndReturnFormattedScore(team.getTeamName()));
        }


    }

    /**
     * This method checks if the the last player is out and if so,
     * it declares the team out and sets it to true
     *
     * @param team {@link TeamModel} takes a team instance
     */
    private static void checkAndSetTeamOut(TeamModel team) {
        if (team.getCurrentPlayer() == LAST_PLAYER) {
            team.setTeamOutToTrue();
            Utils.printMessage(String.format("%s ALL OUT!", team.getTeamName()));
        }
    }


    /**
     * Internally calls the string utils to print message.
     *
     * @param currentMatchNumber Takes the number of the current match that is going on,
     */
    public static void printEndMatchMessage(int currentMatchNumber) {
        Utils.printMessage(String.format(
                "**********************************END MATCH %d*************************** %n",
                currentMatchNumber));

    }


    /**
     * This method takes a team and innings and then reports the number of balls taken by a team
     *
     * @param team {@link TeamModel} the team that just finished innings
     */
    public static void reportOversAndBalls(TeamModel team) {
        int overs = team.getBallsUsed() / 6;
        int numberOfOverBalls = team.getBallsUsed() - (overs * 6);
        Utils.printMessage(String.format("%s used %d overs and %d balls", team.getTeamName(),
                overs, numberOfOverBalls));
    }


    public static void endInningAndReportStats(TeamModel team) {
        MatchOps.reportOversAndBalls(team);
        Utils.printMessage(String.format("%s made %d runs",
                team.getTeamName(), team.getRuns()));
    }


}