package com.example.email;

import com.example.email.objects.Account;
import com.example.email.objects.Contact;
import com.example.email.objects.Mail;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.example.email.EmailService.accountCont;

@RestController
public class EmailController {



    @GetMapping("/")
    public  List<Account> returnAll(){
        return accountCont.findAll();
    }

    @PostMapping("/accounts/create")
    public boolean createAccount(@RequestBody Account account)
    {
        if (accountCont.validate(account))
        {
            accountCont.create(account);
            return  true;
        }
        return false;

    }

    @PostMapping("/accounts/validate")
    public boolean validate(@RequestBody Account account)
    {
        return accountCont.validateLogin(account);
    }


    @GetMapping ("/view")
    public Account view(@RequestParam String account)
    {
        return accountCont.findAccount(account);
    }




    @PostMapping("/mail/create")
    public void createMail(@RequestBody Mail mail){
        accountCont.add(mail);
    }

    @DeleteMapping("/mail/delete")
    public void deleteMail(@RequestParam int id, @RequestParam String account){
        accountCont.deleteMail(id, account);
    }


    @PostMapping("/mail/edit")
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

    @GetMapping("/inboxmode")
    public void mode(@RequestParam String account, @RequestParam String mode)
    {
        accountCont.setMode(mode, account);
    }

    @PostMapping("/addContact")
    public void addContact(@RequestParam String user, @RequestBody Contact contact){
        accountCont.addContact(user, contact);
    }

    @PostMapping("/deleteContact")
    public void deleteContact(@RequestParam String user, @RequestBody Contact contact){
        accountCont.deleteContact(user, contact);
    }

    @PostMapping("/editContact")
    public void editContact(@RequestParam String user, @RequestBody Contact contact){
        accountCont.editContact(user, contact);
    }

    @PostMapping
    public void sendAttachment(final @RequestParam CommonsMultipartFile attachment)
    {}





    
}

