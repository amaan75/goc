package io.github.amaan75;

import io.github.amaan75.utils.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

class MatchOps {

    //this field will hold the index of last player number who will bat,
    //since a player can't bat alone in cricket
    private final static int LAST_PLAYER = 9;
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
        System.out.println("Runs For " + team.getTeamName() + " are:" + team.getRuns());
//        if (currPlayerIndex == LAST_PLAYER) {
//            team.setTeamOutToTrue();
//        }
    }


    static void declareWinner(@NotNull Team team1, @NotNull Team team2) {
        int run1 = team1.getRuns();
        int run2 = team2.getRuns();
        if (run1 > run2)
            System.out.println("Team 1 wins by " + (run1 - run2) + " runs");
        else if (run1 == run2)
            System.out.println("This match was a draw");
        else {
//            System.out.println("Team 2 wins by " + (run2 - run1) + " runs");
            System.out.println("Team 2 won by " + team2.getPlayerRemainingCount() + " wickets");
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


    static void startInning(@NotNull Team team, Innings innings) {
//        StringUtils.printMessage(String.format(""));
        switch (innings.getInningsNumber()) {
            case 1:
                playInning(team, innings);
                break;
            case 2:
                playInning(team, innings, team.getRuns());
                break;
//            case 3:
//                StringUtils.printMessage("NOTHING SO FAR");
//                break;
//            case 4:
//                break;
            default:
                StringUtils.printMessage("ONLY HAS SUPPORT FOR TWO INNINGS So far");
                //cant have more than that
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
            }
            if ()

        }

    }


//    static void startInning(@NotNull Team team, Innings inning, int runs) {
//        System.out.println("Starting " + inning.getInningName() + " Inning");
//        System.out.println(team.getTeamName() + " plays: ");
//        while (!team.isTeamOut() &&
//                inning.getCurrentBall() < Innings.TOTAL_BALLS &&
//                team.getRuns() < runs) {
//            int res = MatchOps.playBall();
//            inning.increaseBallCount();
//            team.addRun(res);
//            if (res > 6) {
//                MatchOps.playerOut(team);
//            }
//        }
//    }

    /**
     * internally calls the string utils to print message.
     *
     * @param currentMatchNumber Takes the number of the current match that is going on,
     */
    public static void printEndMatchMessage(int currentMatchNumber) {
        StringUtils.printMessage(String.format(
                "**********************************END MATCH %d*************************** %n",
                currentMatchNumber));

    }


}