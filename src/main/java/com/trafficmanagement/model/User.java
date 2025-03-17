package com.trafficmanagement.model;

public class User {
    private String idCard;
    private String password;

    public User() {}

    public User(String idCard, String password) {
        this.idCard = idCard;
        this.password = password;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
