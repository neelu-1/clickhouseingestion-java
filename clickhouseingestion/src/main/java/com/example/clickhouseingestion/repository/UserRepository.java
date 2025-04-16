package com.example.clickhouseingestion.repository;

import com.example.clickhouseingestion.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> findAllUsers() {
        return jdbcTemplate.query("SELECT * FROM users", new BeanPropertyRowMapper<>(User.class));
    }

    public void insertUser(int id, String name, String email) {
        jdbcTemplate.update("INSERT INTO users (id, name, email) VALUES (?, ?, ?)", id, name, email);
    }

}
