package io.github.amaan75;

public class Innings {

    static final int TOTAL_BALLS = 20 * 6;

    private int ball = 0;

    private String inningName;

    private Innings(String inningName) {
        this.inningName = inningName;
    }

    void increaseBallCount() {
        ball++;
    }

    public int getBall() {
        return ball;
    }

    public String getInningName() {
        return inningName;
    }

    static class InningsBuilder {

        static Innings firstInning;

        static Innings secondInning;

        public static Innings getFirstInning() {
            if (firstInning == null) firstInning = new Innings("First");
            return firstInning;
        }

        public static Innings getSecondInning() {
            if (secondInning == null) secondInning = new Innings("Second");
            return secondInning;
        }

        public static void refreshInstances() {
            firstInning = null;
            secondInning = null;
        }
    }


}
