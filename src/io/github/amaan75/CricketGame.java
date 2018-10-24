package io.github.amaan75;

import static io.github.amaan75.Innings.InningsEnum.FIRST_INNING;
import static io.github.amaan75.Innings.InningsEnum.SECOND_INNING;
import static io.github.amaan75.Team.TeamEnum.TEAM_1;
import static io.github.amaan75.Team.TeamEnum.TEAM_2;

public class CricketGame {

    public static void main(String[] args) {
        int numberOfMatches = 2;
        if (args.length != 0) {
            numberOfMatches = Integer.parseInt(args[0]);
            System.out.println("COMMAND LINE ARGUMENT FOUND, RUNNING " + numberOfMatches + " matches");
        } else
            System.out.println("NO COMMAND LINE ARGS FOUND, USING 2 MATCHES FOR DEFAULT");
        for (int i = 1; i <= numberOfMatches; i++)
            new MatchController(
                    TEAM_1.getCurrentTeam(),
                    TEAM_2.getCurrentTeam(),
                    FIRST_INNING.getCurrentInning(),
                    SECOND_INNING.getCurrentInning()
            );

    }


}
