package io.github.amaan75;

import org.jetbrains.annotations.NotNull;

public class Player {

    private short id;

    private int runs;

    private boolean out;

    private Player(@NotNull Builder builder) {
        id = builder.id;
        runs = builder.runs;
        out = builder.out;
    }

    public static class Builder {
        private short id;
        private int runs = 0;
        private boolean out = false;

        Builder(short id) {
            this.id = id;
        }

        Player build() {
            return new Player(this);
        }
    }

    public void setPlayerOut() {
        out = true;
    }

    public boolean isOut() {
        return out;
    }

    @Override
    public String toString() {
        return "{Player:" + id + " out:" + out + "}";
    }
}
