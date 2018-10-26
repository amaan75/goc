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
        totalMatchLimit = numberOfMatches;
    }


    /**
     * The playGame method is used to play the game between  2 teams a specified number
     * of times
     */
    void playGame() {
        for (int i = 0; i < totalMatchLimit; i++) {
            team1 = new Team("Team 1");
            team2 = new Team("Team 2");
            match = new Match(team1, team2);
            match.playGame(matchCounter++);
        }


    }
}