package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Account;
import com.example.demo.service.AccountService;

@RestController
@RequestMapping
public class AccountController {
    
    @Autowired
    private AccountService service;

    @GetMapping("/api/accounts")
    public List<Account> getAccounts(){
        return service.findAllAccounts();
    }
    
}
