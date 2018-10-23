package io.github.amaan75;

public class CricketGame {

    public static void main(String[] args) {
        int numberOfMatches = 2;
        if (args.length != 0) {
            numberOfMatches = Integer.parseInt(args[0]);
        } else
            System.out.println("NO COMMAND LINE ARGS FOUND, USING 2 MATCHES FOR DEFAULT");
        for (int i = 1; i <= numberOfMatches; i++)
            new MatchController(
                    Team.getTeam1(),
                    Team.getTeam2(),
                    Innings.InningsBuilder.getFirstInning(),
                    Innings.InningsBuilder.getSecondInning()
            );

    }


}
