package com.avans2018.klasd.cineapp.util_layer;

/**
 * Created by Bas van Rooten on 28-3-2018.
 * AVANS HOGESCHOOL 2125132
 */

public class StringLimiter {

    public static String limit(String input, int length) {
        StringBuilder stringBuilder = new StringBuilder(input);

        if (stringBuilder.length() > length) {
            stringBuilder.setLength(length - 4);
            stringBuilder.append(" ...");
        }

        return stringBuilder.toString();
    }
}