package ch.bzz.webBanking.model;

public class Account {
    private String accountUUID;
    private double balance;
    private String password;
    private String address;
    
    public void changeBalance(double amount) {
        balance += amount;
    }

    public String getAccountUUID() {
        return accountUUID;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
