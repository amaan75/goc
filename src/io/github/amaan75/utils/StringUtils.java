package io.github.amaan75.utils;

import org.jetbrains.annotations.Contract;

public class StringUtils {

    @Contract(" -> fail")
    private StringUtils() {
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
