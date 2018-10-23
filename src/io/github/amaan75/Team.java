package io.github.amaan75;

import java.util.ArrayList;
import java.util.List;

public class Team {

    //two static refs to hold two team instances
    //these will be the only way to acquire teams other than reflection
    private static Team team1;

    private static Team team2;

    // This is the list of players in this team.
    private List<Player> playerList;

    //variable to hold the status of the
    // entire team out or not.
    private boolean teamOut = false;

    //this is the currentPlayer Number which got out.
    private short currentPlayer = 0;

    // runs for this team
    private int runs = 0;

    //this is a variable to store team name
    private String teamName;

    public Player getCurrentPlayer(int index) {
        if (index > playerList.size() - 1 || index < 0) {
            throw new IndexOutOfBoundsException("the index is wrong");
        }
        return playerList.get(index);
    }

    public int getCurrentPlayerAndRemove() {
        return currentPlayer++;
    }

    //there can only be two teams at any moment in a match
    private Team(String teamName) {
        this.teamName = teamName;
        initPlayers();
    }


    private void initPlayers() {
        playerList = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            playerList.add(new Player.Builder((short) i).build());
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Player player : playerList) {
            stringBuilder.append(player);//.append("\n");
        }
        return stringBuilder.toString();
    }


    public void addRun(int value) {
        runs += value;
    }


    public static Team getTeam1() {
        if (team1 == null) team1 = new Team("Team 1");
        return team1;
    }

    public static Team getTeam2() {
        if (team2 == null) team2 = new Team("Team 2");
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

    public void declareTeamOut() {
        teamOut = true;
    }

    public String getTeamName() {
        return teamName;
    }


    static class Builder {

        Builder() {

        }

    }
}
