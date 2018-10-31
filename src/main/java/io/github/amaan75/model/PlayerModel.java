package io.github.amaan75.dao;

import org.jetbrains.annotations.NotNull;

public class PlayerDao {

    private long id;

    private String playerName;

    private int runs;

    private boolean out;

    private PlayerDao(@NotNull Builder builder) {
        id = builder.id;
        playerName = builder.playerName;
        runs = builder.runs;
        out = builder.out;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getRuns() {
        return runs;
    }


    public void addRuns(int runsToAdd) {
        runs += runsToAdd;
    }

    //Builder class is the Builder Pattern from Effective Java
    //used to instantiate the player class
    public static class Builder {
        private long id;
        private int runs = 0;
        private boolean out = false;
        private String playerName;

        public Builder(int id) {
            this.id = id;
            playerName = "a" + id;
        }


        public Builder playerName(String name) {
            playerName = name;
            return this;
        }

        public PlayerDao build() {
            return new PlayerDao(this);
        }
    }

    /**
     * This method declares the player as out
     */
    public void setPlayerOut() {
        out = true;
    }

    public boolean isOut() {
        return out;
    }

    @Override
    public String toString() {
        return "{PlayerDao:" + id + " out:" + out + "}";
    }
}
