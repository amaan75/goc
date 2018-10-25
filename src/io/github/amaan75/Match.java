package io.github.amaan75;

public class Match {


    private Team team1;
    private Team team2;
    private Innings firstInnings;
    private Innings secondInnings;

    Match(Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
    }

    public void playGame(int currentMatchNumber) {
        MatchOps.printStartMatchMessage(currentMatchNumber);
        MatchOps.startInning(team1, new Innings("FIRST", 1));
        MatchOps.startInning(team2, new Innings("SECOND", 2));
        MatchOps.printEndMatchMessage(currentMatchNumber);
    }
}
