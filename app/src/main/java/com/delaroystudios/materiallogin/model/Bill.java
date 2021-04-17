package com.delaroystudios.materiallogin.model;

public class Bill {


    private String bill_id, bill_type,bill_payment_id;


    public Bill() {
    }

    public Bill(String bill_id, String bill_type, String bill_payment_id) {
        this.bill_id = bill_id;
        this.bill_type = bill_type;
        this.bill_payment_id = bill_payment_id;
    }

    public String getBill_id() {
        return bill_id;
    }

    public void setBill_id(String bill_id) {
        this.bill_id = bill_id;
    }

    public String getBill_type() {
        return bill_type;
    }

    public void setBill_type(String bill_type) {
        this.bill_type = bill_type;
    }

    public String getBill_payment_id() {
        return bill_payment_id;
    }

    public void setBill_payment_id(String bill_payment_id) {
        this.bill_payment_id = bill_payment_id;
    }
}
