package com.delaroystudios.materiallogin.model;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.ServerTimestamp;

public class Payment {

    private String payment_id;
    private double payment_amount;
    private @ServerTimestamp Timestamp payment_date;
    private String payment_transaction_id;


    public Payment() {
    }

    public Payment(String payment_id, double payment_amount, Timestamp payment_date, String payment_transaction_id) {
        this.payment_id = payment_id;
        this.payment_amount = payment_amount;
        this.payment_date = payment_date;
        this.payment_transaction_id = payment_transaction_id;
    }


    public String getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(String payment_id) {
        this.payment_id = payment_id;
    }

    public double getPayment_amount() {
        return payment_amount;
    }

    public void setPayment_amount(double payment_amount) {
        this.payment_amount = payment_amount;
    }

    public Timestamp getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(Timestamp payment_date) {
        this.payment_date = payment_date;
    }

    public String getPayment_transaction_id() {
        return payment_transaction_id;
    }

    public void setPayment_transaction_id(String payment_transaction_id) {
        this.payment_transaction_id = payment_transaction_id;
    }
}
