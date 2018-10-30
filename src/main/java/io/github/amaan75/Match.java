package io.github.amaan75;

import io.github.amaan75.dao.TeamDao;

public class Match {


    private TeamDao team1;
    private TeamDao team2;
    private Innings firstInnings;
    private Innings secondInnings;
//    private int noOfInnings;

    Match(TeamDao team1, TeamDao team2) {
        this.team1 = team1;
        this.team2 = team2;
    }

    public void playGame(int currentMatchNumber) {
        MatchOps.printStartMatchMessage(currentMatchNumber);
        MatchOps.startInning(team1, firstInnings, 0);
        MatchOps.endInningAndReportStats(team1, firstInnings);
        MatchOps.startInning(team2, secondInnings, team1.getRuns());
        MatchOps.endInningAndReportStats(team2, secondInnings);
        MatchOps.computeAndDeclareWinner(team1, team2);
        MatchOps.printEndMatchMessage(currentMatchNumber);
    }

    public Innings getFirstInnings() {
        return firstInnings;
    }

    public Innings getSecondInnings() {
        return secondInnings;
    }
}
