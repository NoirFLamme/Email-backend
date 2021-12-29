package com.example.email.objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "accounts")
public class Account implements Serializable {
    @Id
    private String id;


    private String name;
    private String password;
    private String email;
    private List<Mail> mails;
    private List<Contact> contacts;

    public Account(String id, String name, String password, String email, List<Mail> mails, List<Contact> contacts) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.mails = mails;
        this.contacts = contacts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Mail> getMails() {
        return mails;
    }

    public void setMails(List<Mail> mails) {
        this.mails = mails;
    }
    public void addMails(Mail mail ) {
        if(this.mails != null){this.mails.add(mail);}
        else{
            this.mails = new ArrayList<Mail>();
            this.mails.add(mail);
        }

    }


    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}