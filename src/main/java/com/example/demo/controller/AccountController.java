package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Account;
import com.example.demo.service.AccountService;

@RestController
@RequestMapping
public class AccountController {
    String TRANSFER = "TRANSFER";
    String DEPOSIT = "DEPOSIT";
    String WITHDRAWL = "WITHDRAWL";
    
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

    @PutMapping("/api/updateBalance")
    @ResponseStatus(HttpStatus.ACCEPTED)
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

    public String deleteAccountById(@RequestBody String acct){
        String acctId = service.deleteAccountByAccountId(acct);
        return "Account Deleted With Id : " + acctId;
    }
}
