package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;


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
    @ResponseStatus(HttpStatus.CREATED)
    public User createNewUser(@RequestBody User newUser){
        return service.createUsers(newUser);
    }

    @GetMapping("/api/users/createUser/{id}")
    public User getUserById(@PathVariable String userId){
        return service.getUserByUserId(userId);
    }

    @DeleteMapping("/api/users/deleteUser/{id}")
    public String deleteUserById(@RequestBody String Id){
        return service.deleteUserByUserId(Id);
    }
}

