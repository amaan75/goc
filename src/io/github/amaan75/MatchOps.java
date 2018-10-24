package io.github.amaan75;

import org.jetbrains.annotations.NotNull;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

class MatchOps {

    //this field will hold the index of last player number who will bat,
    //since a player can't bat alone in cricket
    private final static int LAST_PLAYER = 9;

    //cached because this will be used very often
    private static Random rnd = ThreadLocalRandom.current();

    private MatchOps() {
        //do not instantiate
        throw new AssertionError("This class is only for static methods");
    }

    /**
     * @return returns the runs from 0-7 where 7 stands for a wicket
     */
    static int ball() {
        //bound = 8 because it is exclusive
        return rnd.nextInt(8);
    }

    static void playerOut(@NotNull Team team) {
        int currPlayerIndex = team.getCurrentPlayerAndRemove();
//        System.out.println("CURRENT PLAYER NUMBER: " + currPlayerIndex);
        team.getCurrentPlayer(currPlayerIndex).setPlayerOut();
        System.out.println("Runs For " + team.getTeamName() + " are:" + team.getRuns());
        if (currPlayerIndex == LAST_PLAYER) {
            team.declareTeamOut();
        }
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

}
