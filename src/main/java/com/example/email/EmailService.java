package com.example.email;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class EmailService {

    @Autowired
    private AccountsRepo accountsRepo;



    public List<Account> findAll(){
        return accountsRepo.findAll();
    }

    public Account create(Account account){ return accountsRepo.insert(account); }

    public boolean validate(Account account) {
        Account match = accountsRepo.findByEmail(account.getEmail());
        System.out.println(match);
        if (match == null)
        {
            return true;
        }
        return false;
    }

    public void add(Mail mail) {
        Mail temp = mail;
        temp.setType("Sent");

        Contact sender = mail.getSender();
        Account yes = accountsRepo.findByEmail(sender.getMailAddresses());

        yes.addMails(mail);

        Contact[] recievers = mail.getReceivers();
        accountsRepo.save(yes);
        for (Contact i : recievers) {
            yes = accountsRepo.findByEmail(i.getMailAddresses());
            temp.setType("Inbox");

            yes.addMails(temp);
            accountsRepo.save(yes);
        }
    }

    public void deleteMail(String id, String account) {
        Account temp = accountsRepo.findByEmail(account);
        for (Mail i: temp.getMails()) {
            if (i.getId().equals(id))
            {
                temp.getMails().remove(i);
                accountsRepo.save(temp);
                return;
            }
        }


    }


    public void sort(String id, String account, String method) {
        Account temp = accountsRepo.findByEmail(account);
       Sorter sorter = new Sorter(temp.getMails());
       sorter.sortby(method);
       accountsRepo.save(temp);

    }
}
