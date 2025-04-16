package com.example.clickhouseingestion.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Service
public class ClickHouseService {

    @Value("${clickhouse.host}")
    private String host;

    @Value("${clickhouse.port}")
    private int port;

    @Value("${clickhouse.database}")
    private String database;

    @Value("${clickhouse.username}")
    private String username;

    @Value("${clickhouse.password}")
    private String password;

    @Value("${clickhouse.ssl}")
    private boolean ssl;

    public Connection getConnection() throws SQLException {
        String jdbcUrl = String.format("jdbc:clickhouse://%s:%d/%s?ssl=%s", host, port, database, ssl);
        Properties props = new Properties();
        props.setProperty("user", username);
        props.setProperty("password", password);
        return DriverManager.getConnection(jdbcUrl, props);
    }

    // Example method to test connection
    public void testConnection() {
        try (Connection conn = getConnection()) {
            System.out.println("✅ Connected to ClickHouse Cloud!");
        } catch (SQLException e) {
            System.err.println("❌ Connection failed: " + e.getMessage());
        }
    }
}
