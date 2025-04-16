package com.example.clickhouseingestion.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@RestController
@RequestMapping("/api")
public class ClickHouseExportController {

    @GetMapping("/download")
    public void downloadCsv(HttpServletResponse response) throws Exception {
        String query = "SELECT id, name, email FROM users";
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=users_export.csv");

        try (
                PrintWriter writer = response.getWriter();
                Connection conn = DriverManager.getConnection(
                        "jdbc:clickhouse://ln8431ofup.ap-south-1.aws.clickhouse.cloud:8443/default?ssl=true",
                        "default",
                        "X~8nvrUhRRTo5"
                );
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()
        ) {
            writer.println("id,name,email");

            while (rs.next()) {
                writer.println(rs.getString("id") + "," + rs.getString("name") + "," + rs.getString("email"));
            }
        }
    }
}
