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
    private String withdrawls;
    private Integer balance;
    private String active;

    public Account(String type, String withdrawls, Integer balance) {
        this.type = type;
        this.withdrawls = withdrawls;
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
    public String getWithdrawls() {
        return withdrawls;
    }
    public void setWithdrawls(String withdrawls) {
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

    public static  Object newInstance(String type,String withdrawls,Integer initDeposit) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
        //Constructor[] constr = Account.class.getConstructors();
        Class classToLoad = Account.class;

        Class[] cArg = new Class[3]; //Our constructor has 3 arguments
        cArg[0] = String.class; //First argument is of *object* type Long
        cArg[1] = String.class; //Second argument is of *object* type String
        cArg[2] = Integer.class; //Third argument is of *primitive* type int


        return classToLoad.getDeclaredConstructor(cArg).newInstance(type, withdrawls, initDeposit);
    }

    @Override
    public String toString() {
        return "Accounts [acctId=" + id + ", type=" + type + ", withdrawls=" + withdrawls + ", balance=" + balance
                + ", active=" + active + "]";
    }

}
