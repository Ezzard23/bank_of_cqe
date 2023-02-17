package com.example.demo.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "USERS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    String idNum;
    String firstName;
    String lastName;
    String middleInitial;
    Address billingAddress;
    Address mailingAddress;
    List<String> bankAccounts;
    Boolean active;

    public class Address {
        String address1;
        String address2;
        String postalZip;
        String state;
        String country;
    }

    public User(String firstName, String lastName, String middleInitial, Address billingAddress,
            Address mailingAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleInitial = middleInitial;
        this.billingAddress = billingAddress;
        this.mailingAddress = mailingAddress;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleInitial() {
        return middleInitial;
    }

    public void setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Address getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(Address mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public List<String> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(List<String> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Users [idNum=" + idNum + ", firstName=" + firstName + ", lastName=" + lastName + ", middleInitial="
                + middleInitial + ", billingAddress=" + billingAddress + ", mailingAddress=" + mailingAddress
                + ", bankAccounts=" + bankAccounts + ", active=" + active + "]";
    }
}

