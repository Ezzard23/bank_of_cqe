package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.security.auth.login.AccountException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;
import java.util.logging.Level;
import com.example.demo.logger.*;

import com.example.demo.model.Account;
import com.example.demo.repository.AccountRepository;

@Service
public class AccountService {
    Logger logger = SysLogger.accountlogger();

    @Autowired
    private AccountRepository repository;
    //CRUD

    public Account addAccount(String type, Integer initDeposit){
        Account acct = new Account(type, initDeposit);
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
                throw new AccountException("Deposit amount must be greater than Zero " + deposit);
            }else{
                Integer newBalance = acct.getBalance() + deposit ;
                acct.setBalance(newBalance);
                repository.save(acct);
                logger.log(Level.INFO,"Successful Transaction" );
            }
        }catch(AccountException e){
            logger.log(Level.WARNING,e.getMessage());
        }
        return acct;
    }

    public Account withdrawl(String acctId, Integer withdrawl){
        Account acct = repository.findById(acctId).get();

        try{
            if(acct == null) {
                throw new AccountException("Account not found");
            }
            else if(acct.getBalance() < withdrawl ){
                throw new AccountException("Withdrawl is greater than balance" + acct.getBalance());
            }else{
                Integer newBalance = acct.getBalance() - withdrawl ;
                acct.setBalance(newBalance);
                repository.save(acct);
            }
        }catch(AccountException e){
            logger.log(Level.WARNING,e.getMessage());
            return acct;
        }
        return acct;

    }

    public String transfer(String sendingAcctId, String recievingAcctId,Integer transferAmt){
        List<Account> accts = new ArrayList<Account>();
        
        Account sender = getAccountByAccountId(sendingAcctId);
        Account reciever = getAccountByAccountId(recievingAcctId);

        if(sender != null && reciever != null){
            sender.setBalance(sender.getBalance() - transferAmt);
            reciever.setBalance(sender.getBalance() + transferAmt);
            repository.saveAll(accts);

        } 


        logger.log(Level.FINE,"Transaction Successful");
        return "";
    }


}
