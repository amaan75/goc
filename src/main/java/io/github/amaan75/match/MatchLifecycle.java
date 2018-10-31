package io.github.amaan75.model;

/**
 * This interface represents the phases of a match
 */
public interface MatchLifecycle {

    void startGame();

    void playGame();

    void endGame();

}
