package io.github.amaan75.match;

import io.github.amaan75.model.TeamModel;

/**
 * This interface contains all the call back listeners for the match LifeCycle
 * if somebody wants to hook into the lifecycle of a match, then that class must
 * implement this interface
 */
public interface MatchLifeCycleCallBackListener {

    void startGameCallback();

    void playGameCallback();

    void endGameCallback(TeamModel team1, TeamModel team2);
}
