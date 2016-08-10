package com.example.shanesardinha.codeproject.Utility;

import java.util.Locale;

/**
 * Created by shanesardinha on 2016/08/08.
 */
public class DateTimeUtility {

    public static String getMinuteAndSeconds(int milliseconds)
    {
        int seconds = milliseconds / 1000 ;
        int minutes = (seconds % 3600) / 60;
        seconds = seconds % 60;

        return String.format(Locale.ENGLISH, "%02d:%02d", minutes, seconds);
    }
}
