package io.github.amaan75;

public class Main {

    public static void main(String[] args) {
        int numberOfMatches = 2;
        if (args.length != 0) {
            numberOfMatches = Integer.parseInt(args[0]);
            System.out.println("COMMAND LINE ARGUMENT FOUND, RUNNING " + numberOfMatches + " matches");
        } else
            System.out.println("NO COMMAND LINE ARGS FOUND, USING 2 MATCHES FOR DEFAULT");
        start(numberOfMatches);
    }


    static void start(int numberOfMatches) {
        GamesController gamesController =
                new GamesController(
                        numberOfMatches,
                        "/Users/amangulati/Workspace/Tekion/Java/GameOfCricket/src/main/resources/Teams.json"
                );
        gamesController.playGame();
    }



}