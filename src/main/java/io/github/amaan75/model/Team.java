package io.github.amaan75.model;

import io.github.amaan75.jsonholder.TeamJson;
import io.github.amaan75.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * This class Describes a team and its state when its currently
 * playing in a Match.
 *
 * <b> Not to be confused with a Team within the context of a tournament. </b>
 */
public class Team {

    // This is the list of players in this team.
    private List<Player> playerList;


    //this is a variable to store team name
    private String teamName;


    //there can only be two teams at any moment in a Match
    public Team(String teamName) {
        this(teamName, teamName + "Player");
    }

    /**
     * @param teamName         the name of the team
     * @param playerNamePrefix the prefix to be added to the player of the team
     */
    private Team(String teamName, String playerNamePrefix) {
        this.teamName = teamName;
        initPlayers(playerNamePrefix);
    }

    private Team(TeamJson teamJson) {
        teamName = teamJson.getName();
        playerList = Utils.mapPlayerDtoListToPlayerDaoList(teamJson.getPlayerJsonList());
    }


    public static Team from(TeamJson teamJson) {
        return new Team(teamJson);
    }

    private void initPlayers(String playerNamePrefix) {
        playerList = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            playerList.add(new Player.Builder((short) i)
                    .playerName(playerNamePrefix + i).build());
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


    public String getTeamName() {
        return teamName;
    }


    public List<Player> getPlayerList() {
        return playerList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Team team = (Team) o;

        return teamName.equals(team.teamName);
    }

    @Override
    public int hashCode() {
        return teamName.hashCode();
    }

    public Player getPlayerFromList(int index) {
        return playerList.get(index);
    }
}
