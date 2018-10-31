package io.github.amaan75.dao;

/**
 * This Class maintains the score/state of the team inside a match
 */
public class TeamScore {
    private static final int MAX_WICKETS = 10;
    private int teamRuns;
    private int ballsUsed;
    private int wicketsFallen;
    //variable to hold the status of the
    // entire team out or not.
    private boolean teamOut;
    // this is a reference to the team it is associated with
    private TeamDao team;

    TeamScore(TeamDao team) {
        teamRuns = 0;
        ballsUsed = 0;
        wicketsFallen = 0;
        teamOut = false;
        this.team = team;
    }


    /**
     * @return returns the number wickets left before the team goes allout
     */
    public int getPlayerRemainingCount() {
        return MAX_WICKETS - wicketsFallen;
    }

    /**
     * adds runs to the score of the team
     *
     * @param run int runs to add to the teamRuns
     */
    void addRuns(int run) {
        teamRuns += run;
    }

    //increases the wicket fallen count when a wicket goes down
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

    public boolean isTeamOut() {
        return teamOut;
    }

    public void setTeamOutToTrue() {
        teamOut = true;
    }

    public TeamDao getTeam() {
        return team;
    }

    void increaseBallUsedCount() {
        ballsUsed++;

    }
}
