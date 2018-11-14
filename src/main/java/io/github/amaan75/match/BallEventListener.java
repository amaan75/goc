package io.github.amaan75.match;

import io.github.amaan75.model.Team;

public interface BallEventListener {
    void ballEvent(int ballResult, Team team);
}
