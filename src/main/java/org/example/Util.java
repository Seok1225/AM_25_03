package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Util {
    public static LocalDateTime getNow() {
        return LocalDateTime.now();
    }

    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
