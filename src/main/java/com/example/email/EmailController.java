package com.example.email;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmailController {
    @Autowired
    private EmailService accountCont;

    @Autowired
    private Mailer mailer;


    @GetMapping("/")
    public  List<Account> getAllTodos(){
        return accountCont.findAll();
    }

    @PostMapping("/createAccount")
    public boolean createAccount(@RequestBody Account account)
    {
        if (accountCont.validate(account))
        {
            accountCont.create(account);
            return  true;
        }
        return false;

    }

    @PostMapping("/validate")
    public boolean validate(@RequestBody Account account)
    {
        return accountCont.validateLogin(account);
    }


    @GetMapping("/view")
    public Account view(@RequestBody Account account)
    {
        return accountCont.findAccount(account.getEmail());
    }




    @PostMapping("/createMail")
    public void createMail(@RequestBody Mail mail){
        accountCont.add(mail);
    }

    @DeleteMapping("/deleteMail")
    public void deleteMail(@RequestParam String id, @RequestParam String account){
        accountCont.deleteMail(id, account);
    }


    @PostMapping("/editMail")
    public void editMail(@RequestParam String id, @RequestBody Mail mail){
        accountCont.editMail(id, mail);
    }

    @GetMapping("/sort")
    public void sort(@RequestParam String id, @RequestParam String account, @RequestParam String method)
    {
        accountCont.sort(id, account, method);
    }

    @GetMapping("/search")
    public List<Mail> search(@RequestParam String account, @RequestParam String searchInput)
    {
        return accountCont.search(account, searchInput);
    }


    @PostMapping("/filter")
    public List<Mail> filter(@RequestParam String account, @RequestBody Mail criterias)
    {
        return accountCont.filter(account, criterias);
    }

//    @GetMapping("/inboxmode")
//    public void mode(@RequestParam String account, @RequestParam String mode)
//    {
//        accountCont.setMode(mode, account);
//    }

//    @PostMapping("/addContact")
//    public void addContact(@RequestParam String user, @RequestBody Contact contact){
//        accountCont.addContact(user, contact);
//    }
//
//    @PostMapping("/deleteContact")
//    public void deleteContact(@RequestParam String user, @RequestBody Contact contact){
//        accountCont.deleteContact(user, contact);
//    }
//
//    @PostMapping("/editContact")
//    public void editContact(@RequestParam String user, @RequestBody Contact contact){
//        accountCont.editContact(user, contact);
//    }
//


    
}

