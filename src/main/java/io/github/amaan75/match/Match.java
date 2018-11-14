package io.github.amaan75.match;

import io.github.amaan75.model.Team;

/**
 * This interface represents the phases of a Match
 */
public interface Match {

    void startGame();

    void playGame();

    void endGame(Team team1, Team team2);

}
