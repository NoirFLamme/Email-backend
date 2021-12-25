package com.example.email;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
//
//    public void deleteMail(int id, String account) {
//        for(Account i : accounts)
//        {
//            if(i.getEmail().equals(account))
//            {
//                i.getMails().removeIf(j -> j.getId() == id);
//            }
//        }
//    }


}
