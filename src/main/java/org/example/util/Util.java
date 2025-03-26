package org.example.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Util {

    public static LocalDateTime getNow() {
        return LocalDateTime.now();
    }

    public static String formatDateTimeSmart(LocalDateTime dateTime) {
        LocalDateTime now = getNow();
        if (dateTime.toLocalDate().isEqual(now.toLocalDate())) {
            return dateTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        } else {
            return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
    }
}
