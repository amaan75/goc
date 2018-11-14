package io.github.amaan75;

import io.github.amaan75.model.Team;
import io.github.amaan75.model.TeamScore;
import io.github.amaan75.utils.Utils;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Class with only static methods which represent the operations in a single Match
 */
public class MatchUtils {

    //this field will hold the index of last player number who will bat,
    //since a player can't bat alone in cricket
    private final static int LAST_PLAYER = 10;

    //cached because this will be used very often
    private static Random rnd = ThreadLocalRandom.current();

    @Contract(" -> fail")
    private MatchUtils() {
        //do not instantiate
        throw new AssertionError("This class is only for static methods");
    }

    /**
     * @return returns the runs from 0-7 where 7 stands for a wicket
     */
    public static int playBall() {
        //bound = 8 because it is exclusive
        return rnd.nextInt(8);
    }

    static void declareAndSetPlayerOut(@NotNull Team team, @NotNull TeamScore teamScore) {
        int currPlayerIndex = teamScore.getWicketsFallen();
        Utils.printMessage(String.format("PLAYER %s IS OUT!",
                team.getPlayerFromList(currPlayerIndex).getPlayerName()));
    }


//    /**
//     * This method takes two teams, compares the runs and declares the
//     * winner
//     *
//     * @param team1 1st out of the two playing teams
//     * @param team2 2nd out of the two playing teams
//     */
//    public static void computeAndDeclareWinner(@NotNull Team team1, @NotNull Team team2) {
//
//        int run1 = team1.getRuns();
//        int run2 = team2.getRuns();
//        if (run1 > run2) {
//            Utils.printMessage(String.format("%s wins by %d runs", team1.getTeamName(), (run1 - run2)));
//        } else if (run1 == run2) {
//            Utils.printMessage("This Match was a draw");
//        } else {
//            Utils.printMessage(String.format("%s won by %d wickets", team2.getTeamName(), team2.getPlayerRemainingCount()));
//        }
//
//    }


//
//    /**
//     * This method is the implementation for FirstInning, and takes only two parameters because
//     * we don't have any target for the first Innings
//     *
//     * @param team this method is the implementation for the first Inning.
//     */
//    static void playInning(Team team) {
//        playInning(team, Integer.MAX_VALUE);
//    }

//    /**
//     * OverLoaded method for the inning and this method will also take a 3rd param, the target runs
//     * That the playing team has to beat in order to win.
//     *
//     * @param team       this is the team that currently batting.
//     * @param targetRuns these are the target runs, that the current batting team has to beat.
//     */
//    static void playInning(Team team, int targetRuns) {
//        while (!team.isTeamOut() &&
//                team.getBallsUsed() < TOTAL_BALLS &&
//                team.getRuns() < targetRuns) {
//            //this ball result will store the value that the ball returns either.
//            //0-6 or 7 for bold
//            int ballResult = MatchUtils.playBall();
//            if (ballResult <= 6) {
//                team.addRun(ballResult);
//            } else {
//                MatchUtils.declareAndSetPlayerOut(team);
//                team.callNextPlayer();
//                MatchUtils.checkAndSetTeamOut(team);
//            }
//            team.increaseBallUsedCount();
//        }
//


    /**
     * This method checks if the the last player is out and if so,
     * it declares the team out and sets it to true
     *
     * @param team {@link Team} takes a team instance
     */
    static void checkAndSetTeamOut(Team team, TeamScore teamScore) {
        if (teamScore.getWicketsFallen() == LAST_PLAYER) {
            teamScore.setTeamOutToTrue();
            Utils.printMessage(String.format("%s ALL OUT!", team.getTeamName()));
        }
    }


    /**
     * Internally calls the string utils to print message.
     *
     * @param currentMatchNumber Takes the number of the current Match that is going on,
     */
    public static void printEndMatchMessage(int currentMatchNumber) {
        Utils.printMessage(String.format(
                "**********************************END MATCH %d*************************** %n",
                currentMatchNumber));

    }


    /**
     * This method takes a team and innings and then reports the number of balls taken by a team
     *
     * @param team {@link Team} the team that just finished innings
     */
    private static void reportOversAndBalls(Team team, TeamScore teamScore) {
        int overs = teamScore.getBallsUsed() / 6;
        int numberOfOverBalls = teamScore.getBallsUsed() - (overs * 6);
        Utils.printMessage(String.format("%s used %d overs and %d balls", team.getTeamName(),
                overs, numberOfOverBalls));
    }


    public static void endInningAndReportStats(Team team, TeamScore teamScore) {
        MatchUtils.reportOversAndBalls(team, teamScore);
        Utils.printMessage(String.format("%s made %d runs",
                team.getTeamName(), teamScore.getTeamRuns()));
    }


}