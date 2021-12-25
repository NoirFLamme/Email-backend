package com.example.email;

public class Contact {
    private String name;
    private String mailAddresses; //TODO turn into a list

    public Contact(String name, String mailAddress) {
        this.name = name;
        this.mailAddresses = mailAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMailAddresses() {
        return mailAddresses;
    }

    public void setMailAddresses(String mailAddresses) {
        this.mailAddresses = mailAddresses;
    }
}