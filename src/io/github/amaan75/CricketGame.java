package io.github.amaan75;

import org.jetbrains.annotations.NotNull;

public class CricketGame {

    public static void main(String[] args) {
        // write your code here
//        for (int i = 0; i <= 1; i++) {
////            System.out.println(MatchOps.ball());
//            System.out.println(Team.getTeam1());
//        }

        startInning(Team.getTeam1());
        startInning(Team.getTeam2());

        System.out.println("TEAM 1 RUNS ARE:" + Team.getTeam1().getRuns());
        System.out.println("TEAM 2 RUNS ARE:" + Team.getTeam2().getRuns());
        int run1 = Team.getTeam1().getRuns();
        int run2 = Team.getTeam2().getRuns();
        if (run1 > run2)
            System.out.println("Team 1 wins by " + (run1 - run2) + " runs");
        else
            System.out.println("Team 2 wins by " + (run2 - run1) + " runs");

    }

    static void startInning(@NotNull Team team) {
        int ballNumber = 0;
        while (!team.isTeamOut()) {
            int res = MatchOps.ball();
            ballNumber++;
            team.addRun(res);
            System.out.println(ballNumber);
            if (res > 6) {
                MatchOps.playerOut(team);
                System.out.println(team);
            }
        }
    }
}
