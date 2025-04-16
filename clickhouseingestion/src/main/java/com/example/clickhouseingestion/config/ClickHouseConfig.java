package com.example.clickhouseingestion.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClickHouseConfig {

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

    public String getJdbcUrl() {
        return String.format("jdbc:clickhouse://%s:%d/%s?ssl=%s", host, port, database, ssl);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
