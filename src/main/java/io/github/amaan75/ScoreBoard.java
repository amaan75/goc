package io.github.amaan75;

import io.github.amaan75.match.BallEventListener;
import io.github.amaan75.match.MatchCallBackListener;
import io.github.amaan75.model.Team;
import io.github.amaan75.model.TeamScore;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Contains the instance of two teams that are currently in Match, and can then print their scores
 * by getting the info from them
 */
public class ScoreBoard implements MatchCallBackListener, BallEventListener {
    public Map<Team, TeamScore> getTeamScoreHashMap() {
        return teamScoreHashMap;
    }

    private Map<Team, TeamScore> teamScoreHashMap;


    @Override
    public void matchStarted(LocalDateTime now, Team team1, Team team2) {
        teamScoreHashMap = new HashMap<>();
        teamScoreHashMap.put(team1, new TeamScore());
        teamScoreHashMap.put(team2, new TeamScore());
    }


    @Override
    public void matchFinished(Team team1, Team team2) {
        MatchUtils.computeAndDeclareWinner(team1, team2,
                teamScoreHashMap.get(team1),
                teamScoreHashMap.get(team2));
    }

    @Override
    public void ballEvent(int ballResult, Team battingTeam) {
        TeamScore teamScore = teamScoreHashMap.computeIfAbsent(battingTeam, (unused) -> new TeamScore());
        if (ballResult <= 6) {
            teamScore.addRuns(ballResult);
        } else {
            MatchUtils.declareAndSetPlayerOut(battingTeam, teamScore);
            teamScore.wicketDown();
            MatchUtils.checkAndSetTeamOut(battingTeam, teamScore);
        }
        teamScore.increaseBallUsedCount();


    }
}
