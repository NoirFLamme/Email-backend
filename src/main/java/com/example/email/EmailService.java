package com.example.email;


import com.example.email.filter.*;
import com.example.email.objects.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmailService {

    @Autowired
    private AccountsRepo accountsRepo;


    public List<Account> findAll(){
        return accountsRepo.findAll();
    }

    public Account create(Account account){
        accountsRepo.insert(account);
        return account;
    }

    public boolean validate(Account account) {
        Account match = accountsRepo.findByEmail(account.getEmail());
        System.out.println(match);
        if (match == null)
        {
            return true;
        }
        else {
            return false;
        }

    }



    public Account add(Mail mail) {
        Mail temp = mail;
        temp.setType("Sent");

        Contact sender = mail.getSender();
        Account yes = accountsRepo.findByEmail(sender.getMailAddresses());

        yes.addMails(mail);
        List<Contact> recievers = mail.getReceivers();
        accountsRepo.save(yes);
        for (Contact i : recievers) {
            yes = accountsRepo.findByEmail(i.getMailAddresses());
            temp.setType("Inbox");

            yes.addMails(temp);
            accountsRepo.save(yes);
        }
        return yes;
    }

    public void deleteMail(String id, String account) {
        Account temp = accountsRepo.findByEmail(account);
        for (Mail i: temp.getMails()) {
            if (i.getId().equals(id))
            {
                int index = temp.getMails().indexOf(i);
                i.setType("Trash");
                temp.getMails().set(index, i);
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

    public void editMail(String account, Mail mail) {
        Account temp = accountsRepo.findByEmail(account);
        List<Mail> ye = new ArrayList<>();
        for (Mail i : temp.getMails())
        {
            if (i.getId().equals(mail.getId()))
            {
                i = mail;
                ye.add(mail);
                continue;
            }
            ye.add(i);
        }
        temp.setMails(ye);
        accountsRepo.save(temp);
    }

    public void addContact(String user, Contact contact) {
        Account temp = accountsRepo.findByEmail(user);
        temp.getContacts().add(contact);
        accountsRepo.save(temp);

    }

    public void deleteContact(String user, Contact contact) {
        Account temp = accountsRepo.findByEmail(user);
        temp.getContacts().remove(contact);
        accountsRepo.save(temp);
    }

    public void editContact(String user, Contact contact) {
        Account temp = accountsRepo.findByEmail(user);
        List<Contact> ye = new ArrayList<>();
        for (Contact i : temp.getContacts())
        {
            if (i.getName().equals(contact.getName()))
            {
                ye.add(contact);
                continue;
            }
            ye.add(i);
        }
    }

    public boolean validateLogin(Account account) {
        if (!this.validate(account))
        {
            if (accountsRepo.findByEmail(account.getEmail()).getPassword().equals(account.getPassword()) &&
                accountsRepo.findByEmail(account.getEmail()).getEmail().equals(account.getEmail()))
            {
                return true;
            }
        }
        return false;
    }

    public Account findAccount(String email) {
        return accountsRepo.findByEmail(email);
    }


    public void setMode(String mode, String account) {
        Account temp = accountsRepo.findByEmail(account);
        if (mode.equals("priority"))
        {

            PriorityQueue<Mail> pq = new PriorityQueue<Mail>(new PriorityComparator());
            pq.addAll(temp.getMails());
            System.out.println(pq.peek().getPriority());
            List<Mail> yeye = new ArrayList<>(pq);
            System.out.println(yeye.get(0).getPriority());
            temp.setMails(yeye);
        }
        else{
            Sorter sorter = new Sorter(temp.getMails());
            sorter.sortby("dateUp");
        }

        accountsRepo.save(temp);
    }

//    public void uploadAttachments(Attachment attachment, String email)
//    {
//        Account temp = accountsRepo.findByEmail(email);
//        for (Mail i : temp.getMails())
//        {
//            if (i.getId().equals(attachment.getId()))
//            {
//                i.getAttachment().add(attachment);
//                return;
//            }
//        }
//    }
}
