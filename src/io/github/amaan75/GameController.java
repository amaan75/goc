package io.github.amaan75;

import org.jetbrains.annotations.NotNull;

/**
 * The Game controller is responsible for controlling the entire game,
 */
public class GameController {

    private Team team1;
    private Team team2;
    private int matchCounter = 1;
    private int totalMatchLimit = 0;
    private Match match;


    public GameController(int numberOfMatches) {
        this.team1 = new Team("team1");
        this.team2 = new Team("team2");
        totalMatchLimit = numberOfMatches;
    }

    public GameController(String team1Name, String team2Name, int numberOfMatches) {
        this.team1 = new Team(team1Name);
        this.team2 = new Team(team2Name);
        totalMatchLimit = numberOfMatches;
    }

    /**
     * The playGame method is used to play the game between  2 teams a specified number
     * of times
     */
    void playGame() {
        for (int i = 0; i < totalMatchLimit; i++) {
            match = new Match(team1, team2);
            match.playGame(matchCounter++);
        }


    }
}