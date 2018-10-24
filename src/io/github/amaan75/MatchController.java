package io.github.amaan75;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;

public class MatchController {


    MatchController(Team team1, Team team2, Innings innings1, Innings innings2) {
        playGame(team1, team2, innings1, innings2);
    }

    private void playGame(Team team1, Team team2, Innings innings1, Innings innings2) {
        System.out.println("************************BEGIN MATCH**************************");
        startInning(
                team1,
                innings1,
                Integer.MAX_VALUE
        );
        startInning(
                team2,
                innings2,
                team1.getRuns()
        );

        System.out.println("TEAM 1 RUNS ARE:" + team1.getRuns());
        System.out.println("TEAM 2 RUNS ARE:" + team2.getRuns());
        MatchOps.declareWinner(team1, team2);

        System.out.println("************************END MATCH**************************\n\n\n");
        Team.refreshInstances();
        Innings.InningsBuilder.refreshInstances();
    }


    static void startInning(@NotNull Team team, Innings inning, int runs) {
        System.out.println("Starting " + inning.getInningName() + " Inning");
        System.out.println(team.getTeamName() + " plays: ");
        while (!team.isTeamOut() &&
                inning.getBall() < Innings.TOTAL_BALLS &&
                team.getRuns() < runs) {
            int res = MatchOps.ball();
            inning.increaseBallCount();
            team.addRun(res);
            if (res > 6) {
                MatchOps.playerOut(team);
            }
        }

        System.out.println("Finished Innings");
        int overs = inning.getBall() / 6;
        int numberOfOverBalls = inning.getBall() - (overs * 6);
//        System.out.println("number of balls:" + inning.getBall());
        System.out.println("Overs Used by " + team.getTeamName() +
                " are:" + overs + " overs and " + numberOfOverBalls + " balls");
        System.out.println("Runs for " + team.getTeamName() + " are:" + team.getRuns() + "\n\n\n");
    }

}
