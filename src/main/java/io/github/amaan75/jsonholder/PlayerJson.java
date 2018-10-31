package io.github.amaan75.jsonholder;

/**
 * Class used to hold the parsed JSON data.
 */
public class PlayerJson {
    private String name;

    public PlayerJson(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "PlayerJson{" +
                "name='" + name + '\'' +
                '}';
    }
}

