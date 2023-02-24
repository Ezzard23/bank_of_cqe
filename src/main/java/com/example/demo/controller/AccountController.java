package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;
import java.util.logging.Level;


import com.example.demo.logger.*;
import com.example.demo.model.Account;
import com.example.demo.service.AccountService;

@RestController
@RequestMapping
public class AccountController {
    Logger logger = SysLogger.accountlogger();

    
    @Autowired
    private AccountService service;

    @GetMapping("/api/accounts")
    public List<Account> getAccounts(){
        logger.log(Level.INFO, "Test");
        return service.findAllAccounts();

    }
    
    @GetMapping("/api/accounts/{id}")
    public Account getAccountById(@PathVariable String id){
        return service.getAccountByAccountId(id);
    }

    
    @PostMapping("/api/addAccount")
    @ResponseStatus(HttpStatus.CREATED)
    public String createAccount(@RequestBody Account acct){
        service.addAccount(acct);
        return "Account Created With Id : " + acct.getId();
    }

    public String deleteAccountById(@RequestBody String acct){
        String acctId = service.deleteAccountByAccountId(acct);
        return "Account Deleted With Id : " + acctId;
    }
}
