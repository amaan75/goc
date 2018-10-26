package io.github.amaan75;

public class Match {


    private Team team1;
    private Team team2;
    private Innings firstInnings;
    private Innings secondInnings;
//    private int noOfInnings;

    Match(Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
    }

    public void playGame(int currentMatchNumber) {
        firstInnings = new Innings("FIRST", 1);
        secondInnings = new Innings("SECOND", 2);
        MatchOps.printStartMatchMessage(currentMatchNumber);
        MatchOps.startInning(team1, firstInnings, 0);
        MatchOps.endInningAndReportStats(team1, firstInnings);
        MatchOps.startInning(team2, secondInnings, team1.getRuns());
        MatchOps.endInningAndReportStats(team2, secondInnings);
        MatchOps.computeAndDeclareWinner(team1, team2);
        MatchOps.printEndMatchMessage(currentMatchNumber);
    }


}
