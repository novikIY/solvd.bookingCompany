package com.solvd.bookingcompany.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyConnection {

    private static final Logger LOGGER =
            LogManager.getLogger(MyConnection.class);

    public MyConnection() {
        LOGGER.info(">>> Connection created!");
    }

    public void execute(String query) {
        LOGGER.info("Executing query: " + query);
    }
}
