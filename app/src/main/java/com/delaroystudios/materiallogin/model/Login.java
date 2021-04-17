package com.delaroystudios.materiallogin.model;

public class Login {

    private String login_id, login_username, login_password, login_member_id;

    public Login() {
    }

    public Login(String login_id, String login_username, String login_password, String login_member_id) {
        this.login_id = login_id;
        this.login_username = login_username;
        this.login_password = login_password;
        this.login_member_id = login_member_id;
    }

    public String getLogin_id() {
        return login_id;
    }

    public void setLogin_id(String login_id) {
        this.login_id = login_id;
    }

    public String getLogin_username() {
        return login_username;
    }

    public void setLogin_username(String login_username) {
        this.login_username = login_username;
    }

    public String getLogin_password() {
        return login_password;
    }

    public void setLogin_password(String login_password) {
        this.login_password = login_password;
    }

    public String getLogin_member_id() {
        return login_member_id;
    }

    public void setLogin_member_id(String login_member_id) {
        this.login_member_id = login_member_id;
    }
}
