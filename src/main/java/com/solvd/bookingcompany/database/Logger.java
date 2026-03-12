package com.solvd.bookingcompany.database;

import org.apache.logging.log4j.LogManager;

public class Logger {

    public static final org.apache.logging.log4j.Logger logger =
            LogManager.getLogger(Logger.class);

    public static void info(String message) {
        logger.info(message);
    }

    public static void warn(String message) {
        logger.warn(message);
    }

    public static void error(String message) {
        logger.error(message);
    }
}