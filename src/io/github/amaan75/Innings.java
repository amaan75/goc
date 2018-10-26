package io.github.amaan75;

public class Innings {

    private int currentBall = 1;

    private String inningName;

    private int inningsNumber;

    Innings(String inningName, int inningsNumber) {
        this.inningName = inningName;
        this.inningsNumber = inningsNumber;

    }

    void increaseBallCount() {
        currentBall++;
    }

    public int getCurrentBall() {
        return currentBall;
    }

    public String getInningName() {
        return inningName;
    }

    public int getInningsNumber() {
        return inningsNumber;
    }


//    static class InningsBuilder {
//
//        static Innings firstInning;
//
//        static Innings secondInning;
//
//        public static Innings getFirstInning() {
//            if (firstInning == null) firstInning = new Innings("First");
//            return firstInning;
//        }
//
//        public static Innings getSecondInning() {
//            if (secondInning == null) secondInning = new Innings("Second");
//            return secondInning;
//        }
//
//        public static void refreshInstances() {
//            firstInning = null;
//            secondInning = null;
//        }
//    }


}
