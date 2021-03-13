package ch.bzz.webBanking.model;


public class Client {



    private String clientUUID;
    private String name;
    private String surname;
    private Account account;
    private String email;
    private String homeAddress;
    
    public Client() {
        
    }
    public Client(String name, String surname, Account account, String email, String homeAddress) {
        
        this.name = name;
        this.surname = surname;
        this.account = account;
        this.email = email;
        this.homeAddress = homeAddress;
    }

    public String getClientUUID() {
        return clientUUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }
}
