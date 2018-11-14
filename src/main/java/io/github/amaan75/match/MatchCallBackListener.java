package io.github.amaan75.match;

import io.github.amaan75.model.Team;

import java.time.LocalDateTime;

/**
 * This interface contains all the call back listeners for the Match LifeCycle
 * if somebody wants to hook into the lifecycle of a Match, then that class must
 * implement this interface
 */
public interface MatchCallBackListener {

    void matchStarted(LocalDateTime now, Team team1, Team team2);

    void matchFinished(Team team1, Team team2);
}
