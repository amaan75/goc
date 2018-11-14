package io.github.amaan75.jsonholder;

/**
 * Class used to hold the parsed JSON data.
 */
public class PlayerJson {
    private String name;
    private String role;

    public PlayerJson(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return name;
    }


    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "PlayerJson{" +
                "name='" + name + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}

