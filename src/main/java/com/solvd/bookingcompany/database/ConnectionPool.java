package com.solvd.bookingcompany.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {

    private static ConnectionPool instance;
    private final BlockingQueue<MyConnection> pool;
    private static final int MAX_CONNECTIONS = 5;

    private static final Logger LOGGER =
            LogManager.getLogger(ConnectionPool.class);

    private ConnectionPool() {
        pool = new LinkedBlockingQueue<>(MAX_CONNECTIONS);

        for (int i = 0; i < MAX_CONNECTIONS; i++) {
            pool.add(new MyConnection());
        }

        LOGGER.info("Connection pool created with {} connections", MAX_CONNECTIONS);
    }

    public static synchronized ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    public MyConnection getConnection() {
        try {
            LOGGER.info("Connection requested from pool");
            return pool.take();
        } catch (InterruptedException e) {
            LOGGER.error("Error getting connection", e);
            return null;
        }
    }

    public void releaseConnection(MyConnection connection) {
        if (connection != null) {
            pool.offer(connection);
            LOGGER.info("Connection returned to pool");
        }
    }

    public int getAvailableConnections() {
        return pool.size();
    }
}