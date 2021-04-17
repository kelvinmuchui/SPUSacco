package com.delaroystudios.materiallogin.model;

public class Loan {

    private  String loan_id, loan_type, loan_account_id;
    private double loan_amount;
    private int loan_period;

    public Loan() {
    }

    public Loan(String loan_id, String loan_type, String loan_account_id, double loan_amount, int loan_period) {
        this.loan_id = loan_id;
        this.loan_type = loan_type;
        this.loan_account_id = loan_account_id;
        this.loan_amount = loan_amount;
        this.loan_period = loan_period;
    }


    public String getLoan_id() {
        return loan_id;
    }

    public void setLoan_id(String loan_id) {
        this.loan_id = loan_id;
    }

    public String getLoan_type() {
        return loan_type;
    }

    public void setLoan_type(String loan_type) {
        this.loan_type = loan_type;
    }

    public String getLoan_account_id() {
        return loan_account_id;
    }

    public void setLoan_account_id(String loan_account_id) {
        this.loan_account_id = loan_account_id;
    }

    public double getLoan_amount() {
        return loan_amount;
    }

    public void setLoan_amount(double loan_amount) {
        this.loan_amount = loan_amount;
    }

    public int getLoan_period() {
        return loan_period;
    }

    public void setLoan_period(int loan_period) {
        this.loan_period = loan_period;
    }
}
