package io.github.amaan75;

import io.github.amaan75.match.MatchLifeCycleCallBackListener;
import io.github.amaan75.model.TeamModel;

import java.util.Objects;

/**
 * Contains the instance of two teams that are currently in match, and can then print their scores
 * by getting the info from them
 */
public class ScoreBoard implements MatchLifeCycleCallBackListener {
    private TeamModel team1;
    private TeamModel team2;


    public ScoreBoard(TeamModel team1, TeamModel team2) {
        this.team1 = team1;
        this.team2 = team2;
    }


    /**
     * This method returns a formatted String which represents the current score of the team in match
     *
     * @param teamName the team name takes the name of the two teams amongst the match for which it has to show the score.
     * @return String which represents the score of the team.
     */
    String computeAndReturnFormattedScore(String teamName) {
        if (Objects.equals(team1.getTeamName(), teamName))
            return String.format("%s has %d runs in %d balls " +
                            " and %d wicket ", team1.getTeamName(),
                    team1.getRuns(), team1.getBallsUsed(), team1.getCurrentPlayer());
        else
            return String.format("%s has %d runs in %d balls " +
                            "and %d wickets ", team2.getTeamName(),
                    team2.getRuns(), team2.getBallsUsed(), team2.getCurrentPlayer());
    }

    @Override
    public void startGameCallback() {

    }

    @Override
    public void playGameCallback() {

    }

    @Override
    public void endGameCallback(TeamModel team1, TeamModel team2) {
        MatchUtils.computeAndDeclareWinner(team1, team2);
    }


}
