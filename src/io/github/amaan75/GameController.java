package io.github.amaan75;

import org.jetbrains.annotations.NotNull;

/**
 * The Game controller is responsible for controlling the entire game,
 */
public class GameController {

    private String team1Name;
    private String team2Name;
    private int matchCounter = 1;
    private int totalMatchLimit = 0;


    public GameController(int numberOfMatches) {
        this.team1Name = "Team1";
        this.team2Name = "Team2";
        totalMatchLimit = numberOfMatches;
    }

    public GameController(String team1Name, String team2Name, int numberOfMatches) {
        this.team1Name = team1Name;
        this.team2Name = team2Name;
        totalMatchLimit = numberOfMatches;
    }

    /**
     * The playGame method is used to play the game between  2 teams a specified number
     * of times
     */
    void playGame() {
        for (int i = 0; i < totalMatchLimit; i++) {
            Team team1 = new Team(this.team1Name);
            Team team2 = new Team(this.team2Name);
            Match match = new Match(team1, team2);
            match.playGame(matchCounter++);
            ScoreBoard scoreBoard = new ScoreBoard(match);
            scoreBoard.showScore();
        }


    }
}