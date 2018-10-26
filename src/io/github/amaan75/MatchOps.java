package io.github.amaan75;

import io.github.amaan75.utils.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

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

    static void declareAndSetPlayerOut(@NotNull Team team) {
        int currPlayerIndex = team.getCurrentPlayer();
        team.getCurrentPlayerFromList(currPlayerIndex).setPlayerOut();
        StringUtils.printMessage(String.format("PLAYER %s IS OUT!",
                team.getCurrentPlayerFromList(currPlayerIndex).getPlayerName()));
    }


    /**
     * This method takes two teams, compares the runs and declares the
     * winner
     *
     * @param team1 1st out of the two playing teams
     * @param team2 2nd out of the two playing teams
     */
    static void computeAndDeclareWinner(@NotNull Team team1, @NotNull Team team2) {
        int run1 = team1.getRuns();
        int run2 = team2.getRuns();
        if (run1 > run2)
            StringUtils.printMessage(String.format("%s wins by %d runs", team1.getTeamName(), (run1 - run2)));
        else if (run1 == run2)
            StringUtils.printMessage("This match was a draw");
        else {
            StringUtils.printMessage(String.format("%s won by %d wickets", team2.getTeamName(), team2.getPlayerRemainingCount()));
        }
    }

    /**
     * This method prints a begin match method
     *
     * @param currentMatchCount this is the current Match Count
     */
    static void printStartMatchMessage(int currentMatchCount) {
        StringUtils.printMessage(String.format(
                "**********************************BEGIN MATCH %d*************************** %n",
                currentMatchCount));
    }


    static void startInning(@NotNull Team team, Innings innings, int targetRuns) {
        switch (innings.getInningsNumber()) {
            case 1:
                playInning(team, innings);
                break;
            case 2:
                playInning(team, innings, targetRuns);
                break;
//            case 3:
//                StringUtils.printMessage("NOTHING SO FAR");
//                break;
//            case 4:
//                break;
            default:
                StringUtils.printMessage("ONLY HAS SUPPORT FOR TWO INNINGS SO FAR");
        }

    }


    /**
     * This method is the implementation for FirstInning, and takes only two parameters because
     * we don't have any target for the first Innings
     *
     * @param team   this method is the implementation for the first Inning.
     * @param inning the inning instance that is currently going on.
     */
    static void playInning(Team team, Innings inning) {
        playInning(team, inning, Integer.MAX_VALUE);
    }

    /**
     * OverLoaded method for the inning and this method will also take a 3rd param, the target runs
     * That the playing team has to beat in order to win.
     *
     * @param team       this is the team that currently batting.
     * @param inning     this is the current innings that is going on.
     * @param targetRuns these are the target runs, that the current batting team has to beat.
     */
    static void playInning(Team team, Innings inning, int targetRuns) {
        while (!team.isTeamOut() &&
                inning.getCurrentBall() < TOTAL_BALLS &&
                team.getRuns() < targetRuns) {
            //this ball result will store the value that the ball returns either.
            //0-6 or 7 for bold
            int ballResult = MatchOps.playBall();
            if (ballResult <= 6) {
                inning.increaseBallCount();
                team.addRun(ballResult);
            } else {
                MatchOps.declareAndSetPlayerOut(team);
                team.callNextPlayer();
                MatchOps.checkAndSetTeamOut(team);
            }
        }

    }

    /**
     * This method checks if the the last player is out and if so,
     * it declares the team out and sets it to true
     *
     * @param team {@link Team} takes a team instance
     */
    private static void checkAndSetTeamOut(Team team) {
        if (team.getCurrentPlayer() == LAST_PLAYER) {
            team.setTeamOutToTrue();
            StringUtils.printMessage(String.format("%s ALL OUT!", team.getTeamName()));
        }
    }


    /**
     * Internally calls the string utils to print message.
     *
     * @param currentMatchNumber Takes the number of the current match that is going on,
     */
    public static void printEndMatchMessage(int currentMatchNumber) {
        StringUtils.printMessage(String.format(
                "**********************************END MATCH %d*************************** %n",
                currentMatchNumber));

    }


    /**
     * This method takes a team and innings and then reports the number of balls taken by a team
     *
     * @param team   {@link Team} the team that just finished innings
     * @param inning {@link Innings} the innings that just ended
     */
    public static void reportOversAndBalls(Team team, Innings inning) {
        int overs = inning.getCurrentBall() / 6;
        int numberOfOverBalls = inning.getCurrentBall() - (overs * 6);
        StringUtils.printMessage(String.format("%s used %d overs and %d balls", team.getTeamName(),
                overs, numberOfOverBalls));
    }


    public static void endInningAndReportStats(Team team, Innings inning) {
        MatchOps.reportOversAndBalls(team, inning);
        StringUtils.printMessage(String.format("%s made %d runs",
                team.getTeamName(), team.getRuns()));
    }
}