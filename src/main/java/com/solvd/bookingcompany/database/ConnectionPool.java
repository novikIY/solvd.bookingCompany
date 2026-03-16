package com.solvd.bookingcompany.database;

import org.apache.logging.log4j.LogManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {

    private static ConnectionPool instance;
    private final BlockingQueue<Connection> pool;
    private static final int MAX_CONNECTIONS = 5;

    private final String url = "jdbc:mysql://localhost:1234/bookingdb";
    private final String user = "root";
    private final String password = "password";
    public static final org.apache.logging.log4j.Logger LOGGER =
            LogManager.getLogger(ConnectionPool.class);

    private ConnectionPool() {
        pool = new LinkedBlockingQueue<>(MAX_CONNECTIONS);

        try {
            for (int i = 0; i < MAX_CONNECTIONS; i++) {
                Connection connection = DriverManager.getConnection(url, user, password);
                pool.add(connection);
            }
            LOGGER.info("Connection pool created with {} connections", MAX_CONNECTIONS);

        } catch (SQLException e) {
            LOGGER.error("Error creating connections", e);
        }
    }

    public static synchronized ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            LOGGER.info("Connection requested from pool");
            return pool.take();
        } catch (InterruptedException e) {
            LOGGER.error("Error getting connection", e);
            return null;
        }
    }

    public void releaseConnection(Connection connection) {
        if (connection != null) {
            pool.offer(connection);
            LOGGER.info("Connection returned to pool");
        }
    }

    public int getAvailableConnections() {
        return pool.size();
    }

    public void closeAllConnections() {
        for (Connection connection : pool) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("Error closing connection", e);
            }
        }
        pool.clear();
        LOGGER.info("All connections closed");
    }
}