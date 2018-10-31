package io.github.amaan75;

import io.github.amaan75.dao.TeamDao;

import java.util.Objects;

/**
 * Contains the instance of two teams that are currently in match, and can then print their scores
 * by getting the info from them
 */
class ScoreBoard {
    private TeamDao team1;
    private TeamDao team2;


    ScoreBoard(TeamDao team1, TeamDao team2) {
        this.team1 = team1;
        this.team2 = team2;
    }


    /**
     * This method returns a formatted String which represents the current score of the team in match
     * @param teamName the team name takes the name of the two teams amongst the match for which it has to show the score.
     * @return String which represents the score of the team.
     */
    String  computeAndReturnFormattedScore(String teamName) {
        if(Objects.equals(team1.getTeamName(), teamName))
        return String.format("%s has %d runs in %d balls " +
                        " and %d wicket ",team1.getTeamName(),
                team1.getRuns(),team1.getBallsUsed(),team1.getCurrentPlayer());
        else
        return String.format("%s has %d runs in %d balls " +
                        "and %d wickets ",team2.getTeamName(),
                team2.getRuns(),team2.getBallsUsed(),team2.getCurrentPlayer());
    }
}
