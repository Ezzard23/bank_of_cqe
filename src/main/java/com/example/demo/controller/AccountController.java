package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import java.util.logging.Logger;
import java.util.logging.Level;


import com.example.demo.logger.*;
import com.example.demo.model.Account;
import com.example.demo.service.AccountService;

@RestController
@RequestMapping
public class AccountController {

    Logger logger = SysLogger.accountlogger();


    String TRANSFER = "TRANSFER";
    String DEPOSIT = "DEPOSIT";
    String WITHDRAWL = "WITHDRAWL";
    
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
    public String createAccount(@RequestBody String acctString) throws ParseException{
        
        Object obj = new JSONParser().parse(acctString);
        JSONObject jo = (JSONObject) obj;

        String type = (String) jo.get("type");
        String initDeposit = (String) jo.get("deposit");

        Account acct = service.addAccount(type,Integer.valueOf(initDeposit));
        return "Account Created With Id : " + acct.getId();
    }

    @PutMapping("/api/updateBalance")
    @ResponseStatus(HttpStatus.OK)
    public Account updateBalance(String acctId,String amount,String operation){
        Account acct = service.getAccountByAccountId(acctId);
        if(acct != null){
            if(operation.equals(TRANSFER)){

            }
            if(operation.equals(DEPOSIT)){
                service.deposit(acctId, Integer.valueOf(amount));
            }
            if(operation.equals(WITHDRAWL)){
                service.withdrawl(acctId, Integer.valueOf(amount));
            }
        }
        return acct;
    }

    @DeleteMapping("/api/deleteAccount/{acct}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteAccountById(@PathVariable String acct){
        String acctId = service.deleteAccountByAccountId(acct);
        return "Account Deleted With Id : " + acctId;
    }
}
