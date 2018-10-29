package io.github.amaan75.utils;

import io.github.amaan75.dao.PlayerDao;
import io.github.amaan75.dto.PlayerDto;
import io.github.amaan75.dto.TeamDto;
import org.jetbrains.annotations.Contract;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
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
    public static List<TeamDto> teamParser(String fileName) {
        List<TeamDto> teamDtoList = new ArrayList<>();
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(fileName)) {
            //Read JSON file
            JSONArray teamsList = (JSONArray) jsonParser.parse(reader);

            //Iterate over employee array
            teamDtoList = (List<TeamDto>) teamsList.stream()
                    .map(team -> parseTeamDtoObject((JSONObject) team))
                    .collect(Collectors.toList());

        } catch (IOException | ParseException e) {
            printMessage(e.getMessage());
        }
        return teamDtoList;
    }

    @SuppressWarnings("unchecked cast")
    private static TeamDto parseTeamDtoObject(JSONObject teamJsonObject) {
        List<PlayerDto> playerDtoList;
        JSONArray playersList = (JSONArray) teamJsonObject.get("players");
        playerDtoList = (List<PlayerDto>) playersList.stream()
                .map(player -> parsePlayerDtoObject((JSONObject) player))
                .collect(Collectors.toList());
        return new TeamDto((String) teamJsonObject.get("name"), playerDtoList);

    }

    @SuppressWarnings("unchecked cast")
    private static PlayerDto parsePlayerDtoObject(JSONObject playerJsonObject) {
        return new PlayerDto((String)
                playerJsonObject.getOrDefault("name", "Default Name"));

    }

    public static List<PlayerDao> mapPlayerDtoListToPlayerDaoList(List<PlayerDto> playerDtoList) {
        List<PlayerDao> playerDaoList = new ArrayList<>();

//        playerDtoList.forEach(playerDto ->
// playerDaoList.add(new PlayerDao.Builder().playerName(playerDto.getName()).build()));
        IntStream.range(0, playerDtoList.size()).forEach(index ->
                playerDaoList.add(new PlayerDao.Builder(index)
                        .playerName(playerDtoList.get(index).getName())
                        .build()
                ));

        return playerDaoList;
    }
}
