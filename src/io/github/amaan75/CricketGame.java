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
    }

    static void startInning(@NotNull Team team) {
        int ballNumber = 0;
        while (!team.isTeamOut()) {
            int res = MatchOps.ball();
            ballNumber++;
            team.addRun(res);
            System.out.println(ballNumber);
            if (res > 6) {
                team.setPlayerOut();
                System.out.println(team);
                System.out.println(team.getRuns());
            }
        }
    }
}
