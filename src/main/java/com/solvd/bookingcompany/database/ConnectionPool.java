package com.solvd.bookingcompany.database;

import org.apache.logging.log4j.LogManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {

    private final BlockingQueue<Connection> connections;
    private final String url;
    private final String user;
    private final String password;
    private final int maxConnections;
    public static final org.apache.logging.log4j.Logger LOGGER =
            LogManager.getLogger(ConnectionPool.class);

    public ConnectionPool(String url, String user, String password, int maxConnections) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.maxConnections = maxConnections;

        connections = new ArrayBlockingQueue<>(maxConnections);

        try {
            for (int i = 0; i < maxConnections; i++) {
                Connection conn = DriverManager.getConnection(url, user, password);
                connections.add(conn);
            }
        } catch (SQLException e) {
            LOGGER.error("Error creating connections", e);
        }
    }

    public Connection getConnection() {
        try {
            return connections.take(); // waits if no connections available
        } catch (InterruptedException e) {
            LOGGER.error("Error getting connection", e);
            return null;
        }
    }

    public void releaseConnection(Connection conn) {
        if (conn != null) {
            connections.offer(conn);
        }
    }

    public void closeAllConnections() {
        for (Connection conn : connections) {
            try {
                conn.close();
            } catch (SQLException e) {
                LOGGER.error("Error closing connection", e);
            }
        }
    }

    public int getAvailableConnections() {
        return connections.size();
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public int getMaxConnections() {
        return maxConnections;
    }
}