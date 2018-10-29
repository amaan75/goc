package io.github.amaan75;

public class ScoreBoard {
    private Match match;

    ScoreBoard(Match match) {
        this.match = match;
    }

    void showScore() {
        MatchOps.showScore(match);
    }


}
