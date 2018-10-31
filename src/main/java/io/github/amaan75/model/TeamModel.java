package io.github.amaan75.model;

import io.github.amaan75.jsonholder.TeamJson;
import io.github.amaan75.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * This class Describes a team and its state when its currently
 * playing in a match.
 *
 * <b> Not to be confused with a TeamModel within the context of a tournament. </b>
 */
public class TeamModel {

    // This is the list of players in this team.
    private List<PlayerModel> playerList;


//    //this is the currentPlayer Number which got out.
//    private short currentPlayer = 0;

    //this is a variable to store team name
    private String teamName;

    //this is the teamScore object, this will hold the score of the Team,
    // runs and balls used
    private TeamScore teamScore;

    public PlayerModel getCurrentPlayerFromList(int index) {
        if (index > playerList.size() - 1 || index < 0) {
            throw new IndexOutOfBoundsException("the index is wrong");
        }
        return playerList.get(index);
    }


    public int getCurrentPlayer() {
        if (checkTeamReadyForPlay())
            return teamScore.getWicketsFallen();
        return -1;
    }

    //there can only be two teams at any moment in a match
    public TeamModel(String teamName) {
        this(teamName, teamName + "PlayerModel");
    }

    /**
     * @param teamName         the name of the team
     * @param playerNamePrefix the prefix to be added to the player of the team
     */
    TeamModel(String teamName, String playerNamePrefix) {
        this.teamName = teamName;
        initPlayers(playerNamePrefix);
    }

    private TeamModel(TeamJson teamJson) {
        teamName = teamJson.getName();
        playerList = Utils.mapPlayerDtoListToPlayerDaoList(teamJson.getPlayerJsonList());
    }


    public static TeamModel from(TeamJson teamJson) {
        return new TeamModel(teamJson);
    }

    private void initPlayers(String playerNamePrefix) {
        playerList = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            playerList.add(new PlayerModel.Builder((short) i)
                    .playerName(playerNamePrefix + i).build());
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (PlayerModel player : playerList) {
            stringBuilder.append(player);//.append("\n");
        }
        return stringBuilder.toString();
    }


    public void addRun(int value) {
        if (checkTeamReadyForPlay())
            teamScore.addRuns(value);
    }


    /**
     * this method checks if the team is ready for play
     *
     * @return returns a true if teamScore is not null
     */
    private boolean checkTeamReadyForPlay() {
        if (teamScore == null) {
            throw new NullPointerException("Team is not ready for play yet, call finaliseTeamForPlay()," +
                    "to get it ready");
        }
        return true;
    }


    public boolean isTeamOut() {
        if (checkTeamReadyForPlay())
            return teamScore.isTeamOut();
        return false;
    }

    public int getRuns() {
        if (checkTeamReadyForPlay())
            return teamScore.getTeamRuns();
        //should be unreachable since throw is never handled
        return -1;
    }

    public void setTeamOutToTrue() {
        if (checkTeamReadyForPlay())
            teamScore.setTeamOutToTrue();
    }

    public String getTeamName() {
        return teamName;
    }


    public void callNextPlayer() {
        if (checkTeamReadyForPlay())
            teamScore.wicketDown();

    }

    public int getBallsUsed() {
        if (checkTeamReadyForPlay())
            return teamScore.getBallsUsed();
        //should be unreachable
        return -1;
    }



    public int getPlayerRemainingCount() {
        if (checkTeamReadyForPlay())
            return teamScore.getPlayerRemainingCount();
        //should be unreachable
        return -1;
    }

    public void increaseBallUsedCount() {
        if (checkTeamReadyForPlay())
            teamScore.increaseBallUsedCount();
    }


    public void finaliseTeamForNewGame() {
        teamScore = new TeamScore(this);
    }
}
