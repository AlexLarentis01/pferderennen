package com.larentis.pferderennen.backend.util;

public final class Log {
    // info
    public static void info(String message) {
        System.out.println("[INFO] " + message);
    }

    // warn
    public static void warn(String message) {
        System.out.println("[WARN] " + message);
    }

    // error
    public static void error(String message) {
        System.out.println("[ERROR] " + message);
    }

    // debug
    public static void debug(String message) {
        System.out.println("[DEBUG] " + message);
    }

    /**
     * Logs a message with the given level.
     * 
     * @param level
     *                the level of the message
     * @param message
     *                the message to log
     */

    public static void log(Level level, String message) {
        System.out.println("[" + level.toString() + "] " + message);
    }
}

enum Level {
    DEBUG, INFO, WARN, ERROR
}

// Language: java