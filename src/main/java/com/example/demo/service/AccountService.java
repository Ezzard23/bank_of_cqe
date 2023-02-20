package com.example.demo.service;

import java.util.List;
import java.util.UUID;

import javax.security.auth.login.AccountException;

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

    public Account addAccount(String type,String withdrawls,Integer initDeposit){
        Account acct = new Account(type, withdrawls, initDeposit);
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

    public Account deposit(String acctId, Integer deposit){
        Account acct = repository.findById(acctId).get();
        try{
            if(deposit < 0){
                throw new AccountException("Deposit amount must be greater than Zero" + deposit);
            }else{
                Integer newBalance = acct.getBalance() + deposit ;
                acct.setBalance(newBalance);
                repository.save(acct);
                System.out.println(acct);
            }
        }catch(AccountException e){
            System.out.println(e.getMessage());
        }
        return acct;
    }

    public Account withdrawl(String acctId, Integer withdrawl){
        Account acct = repository.findById(acctId).get();
        try{
            if(acct.getBalance() < withdrawl ){
                throw new AccountException("Withdrawl is greater than balance" + acct.getBalance());
            }else{
                Integer newBalance = acct.getBalance() - withdrawl ;
                acct.setBalance(newBalance);
                repository.save(acct);
            }
        }catch(AccountException e){
            System.out.println(e.getMessage());
        }
        return acct;

    }

    public String transfer(String sendingAcctId, String recievingAcctId){
        System.out.print(recievingAcctId);
        return "";
    }


}
