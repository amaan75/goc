package io.github.amaan75;

import java.util.Arrays;
import java.util.List;

public class Team {

    //two static refs to hold two team instances
    //these will be the only way to acquire teams other than reflection
    private static Team team1;

    private static Team team2;

    //true means player is out, false means player is not out
    private boolean playersArray[];

    private List<Player> playerList;

    //variable to hold the status of the
    // entire team out or not.
    private boolean teamOut = false;

    //this is the currentPlayer Number which got out.
    private short currentPlayer = 0;

    //method used to set a player as out
    public void setPlayerOut() {
        //set this player to out and
        playersArray[currentPlayer++] = true;
        if (currentPlayer == 11) {
            teamOut = true;
        }
    }




    //there can only be two teams at any moment in a match
    private Team() {
        initPlayers();
    }


    private void initPlayers() {
        playersArray = new boolean[11];
        for (int i = 0; i < 11; i++) {
            playersArray[i] = false;
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(playersArray);
    }

    private int runs = 0;

    public void addRun(int value) {
        runs += value;
    }

//    public boolean

    public boolean[] getPlayersArray() {
        //returning a clone, this way the only way to modify
        // the getPlayersArray is using a Team Instance.
        return playersArray.clone();
    }

    public static Team getTeam1() {
        if (team1 == null) team1 = new Team();
        return team1;
    }

    public static Team getTeam2() {
        if (team2 == null) team2 = new Team();
        return team2;
    }

    public static void refreshInstances() {
        team1 = null;
        team2 = null;
    }

    public boolean isTeamOut() {
        return teamOut;
    }

    public int getRuns() {
        return runs;
    }
}
