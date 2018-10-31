package io.github.amaan75;

import io.github.amaan75.dao.TeamDao;

public class Match {


    private TeamDao team1;
    private TeamDao team2;


    Match(TeamDao team1, TeamDao team2) {
        this.team1 = team1;
        this.team2 = team2;
    }

    public void playGame(int currentMatchNumber) {
        team1.finaliseTeamForNewGame();
        team2.finaliseTeamForNewGame();
        ScoreBoard scoreBoard = new ScoreBoard(team1, team2);
        MatchOps.printStartMatchMessage(currentMatchNumber);
        MatchOps.startInning(team1, -1,scoreBoard);
        MatchOps.endInningAndReportStats(team1);
        MatchOps.startInning(team2, team1.getRuns(),scoreBoard);
        MatchOps.endInningAndReportStats(team2);
        MatchOps.computeAndDeclareWinner(team1, team2);
        MatchOps.printEndMatchMessage(currentMatchNumber);
    }


}
