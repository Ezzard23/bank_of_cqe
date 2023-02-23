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

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.example.demo.logger.*;
import com.example.demo.model.Account;
import com.example.demo.service.AccountService;

@RestController
@RequestMapping
public class AccountController {
    private Logger accountlogger (){
        Logger logger = Logger.getLogger(AccountController.class.getName());
        try {
            LogManager.getLogManager().readConfiguration(new FileInputStream("SysLogger.properties"));
        } catch (SecurityException | IOException e1) {
            e1.printStackTrace();
        }        
        logger.setLevel(Level.FINE);
        logger.addHandler(new ConsoleHandler());
        //adding custom handler
        logger.addHandler(new SysHandler());
        try {
            //FileHandler file name with max size and number of log files limit
            Handler fileHandler = new FileHandler("/Users/cqezz/OneDrive/Desktop/bankAppFrontend/demo", 2000, 5);
            fileHandler.setFormatter(new LogFormatter());
            //setting custom filter for FileHandler
            fileHandler.setFilter(new LogFilter());
            logger.addHandler(fileHandler);
            logger.log(Level.CONFIG, "Config data");
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
        return logger;
    }
    

    
    @Autowired
    private AccountService service;

    @GetMapping("/api/accounts")
    public List<Account> getAccounts(){
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
