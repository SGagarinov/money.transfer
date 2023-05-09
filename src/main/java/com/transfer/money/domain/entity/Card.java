package com.transfer.money.domain.entity;

public class Card {

    private String number;
    private String date;
    private Short cvv;
    private String holderName;
    private String holderLastName;
    private Long balance;

    public Card() {
    }

    public Card(String number, String date, Short cvv, String holderName, String holderLastName, Long balance) {
        this.number = number;
        this.date = date;
        this.cvv = cvv;
        this.holderName = holderName;
        this.holderLastName = holderLastName;
        this.balance = balance;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Short getCvv() {
        return cvv;
    }

    public void setCvv(Short cvv) {
        this.cvv = cvv;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public String getHolderLastName() {
        return holderLastName;
    }

    public void setHolderLastName(String holderLastName) {
        this.holderLastName = holderLastName;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }
}
