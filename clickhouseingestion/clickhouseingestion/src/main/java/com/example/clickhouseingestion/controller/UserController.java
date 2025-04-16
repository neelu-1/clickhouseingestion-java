package com.example.clickhouseingestion.controller;

import com.example.clickhouseingestion.model.User;
import com.example.clickhouseingestion.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // Endpoint to insert a user manually (pass id too)
    @PostMapping
    public String createUser(@RequestParam int id, @RequestParam String name, @RequestParam String email) {
        userRepository.insertUser(id, name, email);
        return "User added successfully";
    }

    // Endpoint to fetch all users
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAllUsers();
    }
}
