package io.github.amaan75;

import java.util.ArrayList;
import java.util.List;

/**
 * This class Describes a team and its state when its currently
 * playing in a match.
 *
 * <b> Not to be confused with a Team within the context of a tournament. </b>
 */
public class Team {

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

    public Player getCurrentPlayerFromList(int index) {
        if (index > playerList.size() - 1 || index < 0) {
            throw new IndexOutOfBoundsException("the index is wrong");
        }
        return playerList.get(index);
    }



    int getCurrentPlayer() {
        return currentPlayer;
    }

    //there can only be two teams at any moment in a match
    Team(String teamName) {
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

    public boolean isTeamOut() {
        return teamOut;
    }

    public int getRuns() {
        return runs;
    }

    public void setTeamOutToTrue() {
        teamOut = true;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getPlayerRemainingCount() {
        return 11 - (currentPlayer + 1);
    }


    public void callNextPlayer() {
        if (currentPlayer > 10) {
            return;
        }
        currentPlayer++;
    }
}
