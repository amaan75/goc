package io.github.amaan75;

import org.jetbrains.annotations.NotNull;

public class Player {

    private short id;

    private String playerName;

    //use this later, when adding
    // more things to the game
//    private int runs;

    private boolean out;

    private Player(@NotNull Builder builder) {
        id = builder.id;
        playerName = builder.playerName;
//        runs = builder.runs;
        out = builder.out;
    }

    public String getPlayerName() {
        return playerName;
    }

    //Builder class is the Builder Pattern from Effective Java
    //used to instantiate the player class
    public static class Builder {
        private short id;
        //        private int runs = 0;
        private boolean out = false;
        private String playerName;

        Builder(short id) {
            this.id = id;
            playerName = "a" + id;
        }

        void playerName(String name) {
            playerName = name;
        }


        Player build() {
            return new Player(this);
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
        return "{Player:" + id + " out:" + out + "}";
    }
}
