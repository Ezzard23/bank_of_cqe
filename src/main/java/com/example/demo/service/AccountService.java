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
        repository.deleteById(acctId);
        return acctId;
    }

    public String deposit(String acctId, Integer deposit){
        Account acct = repository.findById(acctId).get();
        Integer newBalance = acct.getBalance() + deposit ;
        acct.setBalance(newBalance);
        return acctId;
    }

    public String withdawl(String acctId, Integer withdrawl){
        Account acct = repository.findById(acctId).get();
        try{
            if(acct.getBalance() < withdrawl ){
                throw new Exception();
            }
        }catch(Exception e){
            
        }
        Integer newBalance = acct.getBalance() - withdrawl ;
        acct.setBalance(newBalance);
        return acctId;

    }

    public String transfer(String sendingAcctId, String recievingAcctId){
        System.out.print(recievingAcctId);
        return "";
    }


}
