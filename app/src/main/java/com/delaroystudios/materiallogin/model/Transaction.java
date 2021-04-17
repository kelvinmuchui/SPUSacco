package com.delaroystudios.materiallogin.model;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class Transaction {


    private String transaction_id, transaction_mode,transaction_account_id, transation_transactiontype_id;
    private double transaction_amount;
    private @ServerTimestamp Timestamp transaction_time;



    public Transaction() {
    }

    public Transaction(String transaction_id, String transaction_mode, String transaction_account_id, String transation_transactiontype_id, double transaction_amount, Timestamp transaction_time) {
        this.transaction_id = transaction_id;
        this.transaction_mode = transaction_mode;
        this.transaction_account_id = transaction_account_id;
        this.transation_transactiontype_id = transation_transactiontype_id;
        this.transaction_amount = transaction_amount;
        this.transaction_time = transaction_time;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public double getTransaction_amount() {
        return transaction_amount;
    }

    public void setTransaction_amount(double transaction_amount) {
        this.transaction_amount = transaction_amount;
    }

    public String getTransaction_mode() {
        return transaction_mode;
    }

    public void setTransaction_mode(String transaction_mode) {
        this.transaction_mode = transaction_mode;
    }

    public String getTransaction_account_id() {
        return transaction_account_id;
    }

    public void setTransaction_account_id(String transaction_account_id) {
        this.transaction_account_id = transaction_account_id;
    }

    public Timestamp getTransaction_time() {
        return transaction_time;
    }

    public void setTransaction_time(Timestamp transaction_time) {
        this.transaction_time = transaction_time;
    }
    public String getTransation_transactiontype_id() {
        return transation_transactiontype_id;
    }

    public void setTransation_transactiontype_id(String transation_transactiontype_id) {
        this.transation_transactiontype_id = transation_transactiontype_id;
    }
}
