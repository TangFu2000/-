package com.jsu.bean;

public class Buyer {
    private Integer buyerId;
    private String buyerName;
    private String email;
    private String password;
    private Double balance;

    public Buyer() {
    }

    public Buyer(Integer buyerId, String buyerName, String email, String password, Double balance) {
        this.buyerId = buyerId;
        this.buyerName = buyerName;
        this.email = email;
        this.password = password;
        this.balance = balance;
    }

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "buyer{" +
                "buyerId=" + buyerId +
                ", buyerName='" + buyerName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                '}';
    }
}
