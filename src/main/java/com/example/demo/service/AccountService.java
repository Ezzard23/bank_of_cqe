package com.example.demo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration;
import org.springframework.stereotype.Service;

import com.example.demo.model.Account;
import com.example.demo.repository.AccountRepository;

@Service
public class AccountService {
    
    @Autowired
    private AccountRepository repository;
    //CRUD

    public Account addAccount(Account acct){
        acct.setId(UUID.randomUUID().toString().split("-")[0]);
        return repository.save(acct);
    }

    public List<Account> findAllAccounts(){
        return repository.findAll();
    }

    public Account getAccountByAccountId(String acctId){
        return repository.findById(acctId).get();
    }

    public String deleteAccountByAccountId(String acctId){
        try {
            repository.deleteById(acctId);
         }catch(Exception e){
            System.out.println(e);
            return e.getMessage();
         }
         return acctId;
    }


}
