package com.delaroystudios.materiallogin.model;

public class Savings {

    private String saving_id, saving_transation_id;
    private double saving_amount, saving_totalamount;

    public Savings() {
    }

    public Savings(String saving_id, String saving_transation_id, double saving_amount, double saving_totalamount) {
        this.saving_id = saving_id;
        this.saving_transation_id = saving_transation_id;
        this.saving_amount = saving_amount;
        this.saving_totalamount = saving_totalamount;
    }

    public String getSaving_id() {
        return saving_id;
    }

    public void setSaving_id(String saving_id) {
        this.saving_id = saving_id;
    }

    public String getSaving_transation_id() {
        return saving_transation_id;
    }

    public void setSaving_transation_id(String saving_transation_id) {
        this.saving_transation_id = saving_transation_id;
    }

    public double getSaving_amount() {
        return saving_amount;
    }

    public void setSaving_amount(double saving_amount) {
        this.saving_amount = saving_amount;
    }

    public double getSaving_totalamount() {
        return saving_totalamount;
    }

    public void setSaving_totalamount(double saving_totalamount) {
        this.saving_totalamount = saving_totalamount;
    }
}
