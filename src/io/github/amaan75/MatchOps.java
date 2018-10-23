package io.github.amaan75;

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

    static void playerOut(Team team) {
        int currPlayerIndex = team.getCurrentPlayerAndRemove();
//        System.out.println("CURRENT PLAYER NUMBER: " + currPlayerIndex);
        team.getCurrentPlayer(currPlayerIndex).setPlayerOut();
        System.out.println("Runs For " + team.getTeamName() + " are:" + team.getRuns());
        if (currPlayerIndex == LAST_PLAYER) {
            team.declareTeamOut();
        }
    }


}
