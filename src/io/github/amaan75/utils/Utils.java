package io.github.amaan75.utils;

import org.jetbrains.annotations.Contract;

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
}
