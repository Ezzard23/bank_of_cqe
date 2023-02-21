package com.example.demo.model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "ACCOUNTS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    
    @Id
    private String id;
    private String type;
    private Boolean withdrawls;
    private Integer balance;
    private String active;

    public Account(String type, Integer balance) {
        this.type = type;
        this.withdrawls = type.equals("savings") ? false : true;
        this.balance = balance;
        this.active = "true";
        
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Boolean getWithdrawls() {
        return withdrawls;
    }
    public void setWithdrawls(Boolean withdrawls) {
        this.withdrawls = withdrawls;
    }
    public Integer getBalance() {
        return balance;
    }
    public void setBalance(Integer balance) {
        this.balance = balance;
    }
    public String getActive() {
        return active;
    }
    public void setActive(String active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Accounts [acctId=" + id + ", type=" + type + ", withdrawls=" + withdrawls + ", balance=" + balance
                + ", active=" + active + "]";
    }

}
