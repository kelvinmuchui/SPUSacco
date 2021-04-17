package com.delaroystudios.materiallogin.model;

public class Member {

    private String member_id, member_name, member_contact, member_account_id;


    public Member() {
    }


    public Member(String member_id, String member_name, String member_contact, String member_account_id) {
        this.member_id = member_id;
        this.member_name = member_name;
        this.member_contact = member_contact;
        this.member_account_id = member_account_id;
    }


    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public String getMember_contact() {
        return member_contact;
    }

    public void setMember_contact(String member_contact) {
        this.member_contact = member_contact;
    }

    public String getMember_account_id() {
        return member_account_id;
    }

    public void setMember_account_id(String member_account_id) {
        this.member_account_id = member_account_id;
    }
}
