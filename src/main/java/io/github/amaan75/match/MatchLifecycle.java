package io.github.amaan75.match;

import io.github.amaan75.model.TeamModel;

/**
 * This interface represents the phases of a match
 */
public interface MatchLifecycle {

    void startGame();

    void playGame();

    void endGame(TeamModel team1, TeamModel team2);

}
