package io.github.amaan75;

import java.util.ArrayList;
import java.util.List;

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

    public int getPlayerRemainingCount() {
        return 11 - (currentPlayer + 1);
    }


    enum TeamEnum {
        TEAM_1("TEAM 1"),
        TEAM_2("TEAM 2");

        private String teamName;

        TeamEnum(String teamName) {
            this.teamName = teamName;
        }

        Team getCurrentTeam() {
            return new Team(this.teamName);
        }
    }
}
