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


    @PostMapping("/createMail")
    public void createMail(@RequestBody Mail mail){
        accountCont.add(mail);
    }

//    @DeleteMapping("/deleteMail")
//    public void deleteMail(@RequestParam int id, @RequestParam String account){
//        accountCont.deleteMail(id, account);
//    }
////
////    @PostMapping("/addContact")
////    public Account addContact(@RequestBody Account user){
//
//    }

    
}

