package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping
public class UserController {
    
    @Autowired
    private UserService service;

    @GetMapping("/api/users")
    public List<User> getUsers(){
        return service.findAllUsers();
    }

    @PostMapping("/api/createAccount")
    public User createNewUser(User newUser){
        return service.createUsers(newUser);
    }

    @GetMapping("/api/users/{id}")
    public User getUserById(String userId){
        return service.getUserByUserId(userId);
    }
}
