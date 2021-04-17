package com.delaroystudios.materiallogin.model;

public class Account {

    String account_id;
    Double account_amountbal;
    String account_type;
    String account_no;

    public Account() {
    }

    public Account(String account_id, Double account_amountbal, String account_type, String account_no) {
        this.account_id = account_id;
        this.account_amountbal = account_amountbal;
        this.account_type = account_type;
        this.account_no = account_no;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public Double getAccount_amountbal() {
        return account_amountbal;
    }

    public double setAccount_amountbal(Double account_amountbal) {
        this.account_amountbal = account_amountbal;
        return 0;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public String getAccount_no() {
        return account_no;
    }

    public void setAccount_no(String account_no) {
        this.account_no = account_no;
    }
}
