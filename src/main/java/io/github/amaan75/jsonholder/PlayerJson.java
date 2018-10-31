package io.github.amaan75.dto;

/**
 * Class used to hold the parsed JSON data.
 */
public class PlayerDto {
    private String name;

    public PlayerDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "PlayerDto{" +
                "name='" + name + '\'' +
                '}';
    }
}

