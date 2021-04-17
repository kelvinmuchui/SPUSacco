package com.delaroystudios.materiallogin.model;

public class Investment {

    private  String investment_id, investment_name,investment_period, investment_transaction_id;

    public Investment() {
    }

    public Investment(String investment_id, String investment_name, String investment_period, String investment_transaction_id) {
        this.investment_id = investment_id;
        this.investment_name = investment_name;
        this.investment_period = investment_period;
        this.investment_transaction_id = investment_transaction_id;
    }

    public String getInvestment_id() {
        return investment_id;
    }

    public void setInvestment_id(String investment_id) {
        this.investment_id = investment_id;
    }

    public String getInvestment_name() {
        return investment_name;
    }

    public void setInvestment_name(String investment_name) {
        this.investment_name = investment_name;
    }

    public String getInvestment_period() {
        return investment_period;
    }

    public void setInvestment_period(String investment_period) {
        this.investment_period = investment_period;
    }

    public String getInvestment_transaction_id() {
        return investment_transaction_id;
    }

    public void setInvestment_transaction_id(String investment_transaction_id) {
        this.investment_transaction_id = investment_transaction_id;
    }
}
