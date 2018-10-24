package io.github.amaan75;

import org.jetbrains.annotations.NotNull;

public class Player {

    private short id;

    //use this later, when adding
    // more things to the game
    private int runs;

    private boolean out;

    private Player(@NotNull Builder builder) {
        id = builder.id;
//        runs = builder.runs;
        out = builder.out;
    }

    //Builder class is the Builder Pattern from Effective Java
    //used to instantiate the player class
    public static class Builder {
        private short id;
        //        private int runs = 0;
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
        System.out.println("Player Number:" + (id + 1) + " is out");
    }

    public boolean isOut() {
        return out;
    }

    @Override
    public String toString() {
        return "{Player:" + id + " out:" + out + "}";
    }
}
