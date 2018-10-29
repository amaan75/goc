package io.github.amaan75.dto;

import java.util.List;

public class TeamDto {
    private String name;

    private List<PlayerDto> playerDtoList;

    public TeamDto(String name, List<PlayerDto> playerDtoList) {
        this.name = name;
        this.playerDtoList = playerDtoList;

    }


    public String getName() {
        return name;
    }

    public List<PlayerDto> getPlayerDtoList() {
        return playerDtoList;
    }

    @Override
    public String toString() {
        return "TeamDto{" +
                "name='" + name + '\'' +
                ", playerDtoList=" + playerDtoList +
                '}';
    }
}
