package io.github.amaan75.jsonholder;

import java.util.List;

/**
 * Class used to hold the parsed JSON data.
 */
public class TeamJson {
    private String name;

    private List<PlayerJson> playerJsonList;

    public TeamJson(String name, List<PlayerJson> playerJsonList) {
        this.name = name;
        this.playerJsonList = playerJsonList;

    }


    public String getName() {
        return name;
    }

    public List<PlayerJson> getPlayerJsonList() {
        return playerJsonList;
    }

    @Override
    public String toString() {
        return "TeamJson{" +
                "name='" + name + '\'' +
                ", playerJsonList=" + playerJsonList +
                '}';
    }
}
