package com.example.email;

import java.util.ArrayList;
import java.util.List;

public class Contacts {
    private final List<Contact> contacts;

    public Contacts() {
        this.contacts = new ArrayList<Contact>();
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void addContact(Contact contact){
        contacts.add(contact);
    }

    public void deleteContact(Contact contact) {
        contacts.remove(contact);
    }

    public void editContactName(Contact contact, String newContactName) {
        contact.setName(newContactName);
    }

    // TODO edit_email_addresses()
}