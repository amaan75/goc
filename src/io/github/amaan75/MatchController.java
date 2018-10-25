package io.github.amaan75;

import org.jetbrains.annotations.NotNull;

/**
 * The match controller will controller will take to teams
 * and then the teams will play a bunch of matches with each other
 */
public class MatchController {

    private Team team1;
    private Team team2;
    private int matchCounter = 1;
    private int totalMatchLimit = 0;
    private Match match;

//    /**
//     * The match controller will control the match play between the two teams
//     *
//     * @param team1 {@link Team} Takes a team instance that will play with each other
//     * @param team2 {@link Team} aslo takes a team instance that will play with each other
//     */
//    MatchController(Team team1, Team team2) {
//        this.team1 = team1;
//        this.team2 = team2;
//    }

    public MatchController(int numberOfMatches) {
        totalMatchLimit = numberOfMatches;

    }

    /**
     *
     */
    void playGame(int numberOfMatches) {
        match = new Match(new Team("Australia"), new Team("India"));
        match.playGame(matchCounter);


    }

//    private void playGame(Team team1, Team team2, Innings innings1, Innings innings2) {
//        System.out.println("************************BEGIN MATCH**************************");
//        startInning(
//                team1,
//                innings1,
//                Integer.MAX_VALUE
//        );
//        startInning(
//                team2,
//                innings2,
//                team1.getRuns()
//        );
//
//        System.out.println("TEAM 1 RUNS ARE:" + team1.getRuns());
//        System.out.println("TEAM 2 RUNS ARE:" + team2.getRuns());
//        MatchOps.declareWinner(team1, team2);
//
//        System.out.println("************************END MATCH**************************\n\n\n");
//    }



//
//        System.out.println("Finished Innings");
//    int overs = inning.getCurrentBall() / 6;
//    int numberOfOverBalls = inning.getCurrentBall() - (overs * 6);
////        System.out.println("number of balls:" + inning.getCurrentBall());
//        System.out.println("Overs Used by "+team.getTeamName()+
//                " are:"+overs +" overs and "+numberOfOverBalls +" balls");
//        System.out.println("Runs for "+team.getTeamName()+" are:"+team.getRuns()+"\n\n\n");
//}

}
