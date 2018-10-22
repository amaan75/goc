package io.github.amaan75;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

class MatchOps {

    //cached because this will be used very often
    private static Random rnd = ThreadLocalRandom.current();

    private MatchOps() {
        //do not instantiate
        throw new AssertionError("This class is only for static methods");
    }

    /**
     * @return returns the runs from 0-7 where 7 stands for a wicket
     */
    static int ball() {
        //bound = 8 because it is exclusive
        return rnd.nextInt(8);
    }


}
