package com.routex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Singleton GOF Pattern: ensures a single, shared access point for database connections.
 */
public final class DatabaseConnection {
    private static volatile DatabaseConnection instance;

    private final String url;
    private final String username;
    private final String password;

    private DatabaseConnection() {
        this.url = System.getenv().getOrDefault(
                "ROUTEX_DB_URL",
                "jdbc:sqlserver://localhost:1433;databaseName=RouteX;encrypt=true;trustServerCertificate=true"
        );
        this.username = System.getenv().getOrDefault("ROUTEX_DB_USER", "sa");
        this.password = System.getenv().getOrDefault("ROUTEX_DB_PASSWORD", "YourStrong!Passw0rd");
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
