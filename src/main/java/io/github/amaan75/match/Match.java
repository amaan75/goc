package io.github.amaan75;

import io.github.amaan75.model.MatchLifecycle;
import io.github.amaan75.model.TeamModel;

/**
 * This class is responsible for Handling a single match between two teams
 */
public class Match implements MatchLifecycle {


    private TeamModel team1;
    private TeamModel team2;
    private GamesController gamesController;
    private ScoreBoard scoreBoard;

    Match(TeamModel team1, TeamModel team2) {
        this.team1 = team1;
        this.team2 = team2;
    }


    // this method registers the callback listener
    // gamesController in our case
    void registerCallBackListener(GamesController gamesController,
                                  ScoreBoard scoreBoard) {
        this.gamesController = gamesController;
        this.scoreBoard = scoreBoard;
    }


    @Override
    public void startGame() {
        if (gamesController != null)
            gamesController.startGameCallBack();
        team1.finaliseTeamForNewGame();
        team2.finaliseTeamForNewGame();
        playGame();
    }

    @Override
    public void playGame() {
        if (gamesController != null)
            gamesController.playGameCallBack();
        ScoreBoard scoreBoard = new ScoreBoard(team1, team2);
        MatchUtils.startInning(team1, -1, scoreBoard);
        MatchUtils.endInningAndReportStats(team1);
        MatchUtils.startInning(team2, team1.getRuns(), scoreBoard);
        MatchUtils.endInningAndReportStats(team2);
        MatchUtils.computeAndDeclareWinner(team1, team2);
        endGame();
    }

    @Override
    public void endGame() {
        if (gamesController != null)
            gamesController.endGameCallBack();

    }
}
