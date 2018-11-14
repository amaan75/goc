package io.github.amaan75.model;

import org.jetbrains.annotations.NotNull;

public class Player {

    private long id;

    private String playerName;

    private PlayerRole playerRole;


    //todo: may be have playerStats.

    private Player(@NotNull Builder builder) {
        id = builder.id;
        playerName = builder.playerName;
        playerRole = builder.playerRole;

    }

    public String getPlayerName() {
        return playerName;
    }


    //Builder class is the Builder Pattern from Effective Java
    //used to instantiate the player class
    public static class Builder {
        private long id;
        private String playerName;
        private PlayerRole playerRole;

        public Builder(int id) {
            this.id = id;
            playerName = "a" + id;
        }


        public Builder playerName(String name) {
            playerName = name;
            return this;
        }

        public Builder playerRole(String role) {
            playerRole = role.equals("BAT") ? PlayerRole.BATSMAN : PlayerRole.BOWLER;
            return this;
        }

        public Player build() {
            return new Player(this);
        }
    }


    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", playerName='" + playerName + '\'' +
                '}';
    }
}
