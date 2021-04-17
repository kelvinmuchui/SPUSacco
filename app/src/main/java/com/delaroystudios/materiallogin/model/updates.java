package com.delaroystudios.materiallogin.model;

public class updates {


    private String update_id,update_message, update_account_id;


    public updates() {
    }


    public updates(String update_id, String update_message, String update_account_id) {
        this.update_id = update_id;
        this.update_message = update_message;
        this.update_account_id = update_account_id;
    }

    public String getUpdate_id() {
        return update_id;
    }

    public void setUpdate_id(String update_id) {
        this.update_id = update_id;
    }

    public String getUpdate_message() {
        return update_message;
    }

    public void setUpdate_message(String update_message) {
        this.update_message = update_message;
    }

    public String getUpdate_account_id() {
        return update_account_id;
    }

    public void setUpdate_account_id(String update_account_id) {
        this.update_account_id = update_account_id;
    }
}
