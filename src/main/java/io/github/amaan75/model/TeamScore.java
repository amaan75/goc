package io.github.amaan75.model;

/**
 * This Class maintains the score/state of the team inside a Match
 */
public class TeamScore {
    private static final int MAX_WICKETS = 10;


    private int teamRuns;
    private int ballsUsed;
    private int wicketsFallen;
    private boolean teamOut;


   public int getCurrentPlayerNumber() {
        return wicketsFallen;
    }


    public TeamScore() {
        teamRuns = 0;
        ballsUsed = 0;
        wicketsFallen = 0;
        teamOut = false;
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
    public void addRuns(int run) {
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

    public void increaseBallUsedCount() {
        ballsUsed++;

    }

}



