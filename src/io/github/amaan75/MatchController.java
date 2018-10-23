package io.github.amaan75;

import org.jetbrains.annotations.NotNull;

public class MatchController {


    MatchController(Team team1, Team team2, Innings innings1, Innings innings2) {
        playGame(team1, team2, innings1, innings2);
    }

    private void playGame(Team team1, Team team2, Innings innings1, Innings innings2) {
        System.out.println("************************BEGIN MATCH**************************");
        Team.refreshInstances();
        Innings.InningsBuilder.refreshInstances();
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
        int run1 = team1.getRuns();
        int run2 = team2.getRuns();
        if (run1 > run2)
            System.out.println("Team 1 wins by " + (run1 - run2) + " runs");
        else if (run1 == run2)
            System.out.println("This match was a draw");
        else
            System.out.println("Team 2 wins by " + (run2 - run1) + " runs");

        System.out.println("************************END MATCH**************************\n\n\n");
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
        System.out.println("Runs for " + team.getTeamName() + " are:" + team.getRuns()+"\n\n\n");
    }

}
