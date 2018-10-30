package io.github.amaan75.dao;

public class TeamScore {
    private static final int MAX_WICKETS = 10;
    private int teamRuns;
    private int ballsUsed;
    private int wicketsFallen;
    private TeamDao team;

    TeamScore(TeamDao team) {
        teamRuns = 0;
        ballsUsed = 0;
        wicketsFallen = 0;
        this.team = team;
    }


    /**
     * @return returns the number wickets left before the team goes allout
     */
    public int getPlayerRemainingCount() {
        return MAX_WICKETS - wicketsFallen;
    }

    void addRuns(int run) {
        teamRuns += run;
    }


    public void wicketDown() {
        wicketsFallen++;
    }

    public int getBallsUsed() {
        return ballsUsed;
    }

    public int getTeamRuns() {
        return teamRuns;
    }

    public int getWicketsFallen() {
        return wicketsFallen;
    }

    public TeamDao getTeam() {
        return team;
    }

    void increaseBallUsedCount() {
        ballsUsed++;

    }
}
