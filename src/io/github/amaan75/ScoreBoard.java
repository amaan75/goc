package io.github.amaan75;

public class ScoreBoard {
    Match match;


    ScoreBoard(Match match) {
        this.match = match;
    }

    String fetchFormattedScore(int inningNumber) {
        if (inningNumber == 1)
            return String.format("%s has %d runs in %d balls ", match.getTeam1().getTeamName(),
                    match.getTeam1().getRuns(), match.getFirstInnings().getCurrentBall());
        else
            return String.format("%s has %d runs in %d balls", match.getTeam2().getTeamName(),
                    match.getTeam2().getRuns(), match.getSecondInnings().getCurrentBall());

    }


}
