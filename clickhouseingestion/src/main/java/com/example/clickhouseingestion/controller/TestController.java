package com.example.clickhouseingestion.controller;

import com.example.clickhouseingestion.service.ClickHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private ClickHouseService clickHouseService;

    @GetMapping("/test-clickhouse")
    public String testConnection() {
        try {
            clickHouseService.testConnection();
            return "✅ ClickHouse connection successful!";
        } catch (Exception e) {
            return "❌ Error: " + e.getMessage();
        }
    }
}
