package com.example.clickhouseingestion.controller;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.sql.*;
import java.util.List;
import java.util.Properties;

@RestController
@RequestMapping("/api")
public class ClickHouseUploadController {

    @Value("${clickhouse.host}")
    private String clickHouseHost;

    @Value("${clickhouse.port}")
    private String clickHousePort;

    @Value("${clickhouse.database}")
    private String clickHouseDatabase;

    @Value("${clickhouse.username}")
    private String clickHouseUsername;

    @Value("${clickhouse.password}")
    private String clickHousePassword;

    @PostMapping("/upload")
    public String uploadCsvToClickHouse(@RequestParam("file") MultipartFile file) {
        try (CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            List<String[]> rows = reader.readAll();

            //String jdbcUrl = "jdbc:clickhouse://" + clickHouseHost + ":" + clickHousePort + "/" + clickHouseDatabase + "?ssl=true";
            String jdbcUrl = "jdbc:clickhouse://ln8431ofup.ap-south-1.aws.clickhouse.cloud:8443/default?ssl=true";
            Properties props = new Properties();
            props.setProperty("user", "default");
            props.setProperty("password", "X~8nvrUhRRTo5");


            try (Connection conn = DriverManager.getConnection(jdbcUrl, clickHouseUsername, clickHousePassword)) {
                String insertSQL = "INSERT INTO users (id, name, email) VALUES (?, ?, ?)";

                try (PreparedStatement stmt = conn.prepareStatement(insertSQL)) {
                    for (String[] row : rows) {
                        stmt.setString(1, row[0]);
                        stmt.setString(2, row[1]);
                        stmt.setString(3, row[2]);
                        stmt.addBatch();
                    }
                    stmt.executeBatch();
                }
            }

            return "Uploaded and inserted into ClickHouse successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
}
