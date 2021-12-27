package com.example.email;


import com.example.email.filter.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

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

    public List<Mail> search(String account, String searchInput) {
        CriteriaMailHeader headerSearch = new CriteriaMailHeader();
        return headerSearch.meetCriteria(accountsRepo.findByEmail(account).getMails(), searchInput);
    }

    public List<Mail> filter(String account, Mail criterias) {
        Criteria filter = new AndCriteria(new CriteriaDate(), new CriteriaReceiver(), new CriteriaSender(), new CriteriaType());
        Account temp = accountsRepo.findByEmail(account);
        return filter.meetCriteria(temp.getMails(), criterias);
    }
}
