package com.solvd.bookingcompany.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.solvd.bookingcompany.database.Logger.logger;

public class ConnectionPool {

    private final ArrayList<Connection> connections = new ArrayList<>();
    private final String url;
    private final String user;
    private final String password;
    private final int maxConnections;

    public ConnectionPool(String url, String user, String password, int maxConnections) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.maxConnections = maxConnections;

        try {
            for (int i = 0; i < maxConnections; i++) {
                Connection conn = DriverManager.getConnection(url, user, password);
                connections.add(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public synchronized Connection getConnection() {
        if (connections.isEmpty()) {
            logger.info("No free connections!");
            return null;
        }
        return connections.remove(connections.size() - 1);
    }

    public synchronized void releaseConnection(Connection conn) {
        connections.add(conn);
    }

    public void closeAllConnections() {
        for (Connection conn : connections) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        connections.clear();
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
