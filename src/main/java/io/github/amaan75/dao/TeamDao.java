package io.github.amaan75.dao;

import io.github.amaan75.dto.TeamDto;
import io.github.amaan75.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * This class Describes a team and its state when its currently
 * playing in a match.
 *
 * <b> Not to be confused with a TeamDao within the context of a tournament. </b>
 */
public class TeamDao {

    // This is the list of players in this team.
    private List<PlayerDao> playerList;

    //variable to hold the status of the
    // entire team out or not.
    private boolean teamOut = false;

//    //this is the currentPlayer Number which got out.
//    private short currentPlayer = 0;

    //this is a variable to store team name
    private String teamName;

    //this is the teamScore object, this will hold the score of the Team,
    // runs and balls used
    private TeamScore teamScore;

    public PlayerDao getCurrentPlayerFromList(int index) {
        if (index > playerList.size() - 1 || index < 0) {
            throw new IndexOutOfBoundsException("the index is wrong");
        }
        return playerList.get(index);
    }


//    public int getCurrentPlayer() {
//        return currentPlayer;
//    }

    //there can only be two teams at any moment in a match
    public TeamDao(String teamName) {
        this(teamName, teamName + "PlayerDao");
    }

    /**
     * @param teamName         the name of the team
     * @param playerNamePrefix the prefix to be added to the player of the team
     */
    TeamDao(String teamName, String playerNamePrefix) {
        this.teamName = teamName;
        initPlayers(playerNamePrefix);
    }

    private TeamDao(TeamDto teamDto) {
        teamName = teamDto.getName();
        playerList = Utils.mapPlayerDtoListToPlayerDaoList(teamDto.getPlayerDtoList());
    }


    public static TeamDao from(TeamDto teamDto) {
        return new TeamDao(teamDto);
    }

    private void initPlayers(String playerNamePrefix) {
        playerList = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            playerList.add(new PlayerDao.Builder((short) i)
                    .playerName(playerNamePrefix + i).build());
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (PlayerDao player : playerList) {
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
            throw new AssertionError("Team is not ready for play yet, call finaliseTeamForPlay()," +
                    "to get it ready");
        }
        return true;
    }

    public boolean isTeamOut() {
        return teamOut;
    }

    public int getRuns() {
        if (checkTeamReadyForPlay())
            return teamScore.getTeamRuns();
        //should be unreachable since throw is never handled
        return -1;
    }

    public void setTeamOutToTrue() {
        teamOut = true;
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

//    public TeamScore getTeamScore() {
//        if (checkTeamReadyForPlay())
//            return teamScore;
//        //should also be unreachable, the app will crash if TeamScore references null anyway
//        return null;
//    }



    public void increaseBallUsedCount() {
        if (checkTeamReadyForPlay())
            teamScore.increaseBallUsedCount();
    }


    public void finaliseTeamForNewGame() {
        teamScore = new TeamScore(this);
    }
}
