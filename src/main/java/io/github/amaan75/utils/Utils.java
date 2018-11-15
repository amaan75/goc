package io.github.amaan75.utils;

import io.github.amaan75.model.Player;
import io.github.amaan75.jsonholder.PlayerJson;
import io.github.amaan75.jsonholder.TeamJson;
import org.jetbrains.annotations.Contract;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Utils {

    @Contract(" -> fail")
    private Utils() {
        //do not instantiate
        throw new AssertionError("please don't instantiate this");
    }

    /**
     * This can work as Mock Logger
     *
     * @param message Takes a message and then prints it
     */
    public static void printMessage(String message) {
        System.out.println(message);
    }


    @SuppressWarnings("unchecked cast")
    public static List<TeamJson> teamParser(String fileName) {
        List<TeamJson> teamJsonList = new ArrayList<>();
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(fileName)) {
            //Read JSON file
            JSONArray teamsList = (JSONArray) jsonParser.parse(reader);

            //Iterate over employee array
            teamJsonList = (List<TeamJson>) teamsList.stream()
                    .map(team -> parseTeamDtoObject((JSONObject) team))
                    .collect(Collectors.toList());

        } catch (IOException | ParseException e) {
            printMessage(e.getMessage());
        }
        return teamJsonList;
    }

    @SuppressWarnings("unchecked cast")
    private static TeamJson parseTeamDtoObject(JSONObject teamJsonObject) {
        List<PlayerJson> playerJsonList;
        JSONArray playersList = (JSONArray) teamJsonObject.get("players");
        playerJsonList = (List<PlayerJson>) playersList.stream()
                .map(player -> parsePlayerDtoObject((JSONObject) player))
                .collect(Collectors.toList());
        return new TeamJson((String) teamJsonObject.get("name"), playerJsonList);

    }

    @SuppressWarnings("unchecked cast")
    private static PlayerJson parsePlayerDtoObject(JSONObject playerJsonObject) {
        return new PlayerJson((String)
                playerJsonObject.getOrDefault("name", "Default Name"),
                (String) playerJsonObject.getOrDefault("role", "BAT"));

    }

    public static List<Player> mapPlayerDtoListToPlayerDaoList(List<PlayerJson> playerJsonList) {
        List<Player> playerList = new ArrayList<>();

//        playerJsonList.forEach(playerDto ->
// playerList.add(new Player.Builder().playerName(playerDto.getName()).build()));
        IntStream.range(0, playerJsonList.size()).forEach(index ->
                playerList.add(new Player.Builder(index)
                        .playerName(playerJsonList.get(index).getName())
                        .playerRole(playerJsonList.get(index).getRole())
                        .build()
                ));

        return playerList;
    }
}
