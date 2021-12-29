package com.example.email;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@CrossOrigin
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

    @PostMapping("/accounts/create")
    public boolean createAccount(@RequestBody Account account)
    {
        if (!accountCont.checkIfEmailExists(account))
        {
            accountCont.create(account);
            return  true;
        }
        return false;
    }

    @PostMapping("/accounts/validate")
    public boolean validateLogin(@RequestBody Account account)
    {
        return accountCont.validateLogin(account);
    }


    @GetMapping("/accounts/get")
    public Account getAccount(@RequestParam String email)
    {
        return accountCont.findAccount(email);
    }

    @GetMapping("/mail/get")
    public List<Mail> getMails(@RequestParam String email, @RequestParam String folder)
    {
        return accountCont.getMails(email, folder);
    }


    private static String UPLOADED_FOLDER = "/Users/Zyad/Code/dbtest"; // Change this
    @PostMapping("/file/upload")
    @ResponseBody

    public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("Info") String Info) {
        System.out.println("Json is" + Info);
        if (file.isEmpty()) {
            return "No file attached";
        }
        try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Succuss";
    }

    @PostMapping("/mail/create")
    public void createMail(@RequestBody Mail mail){
        accountCont.add(mail);
    }

    @DeleteMapping("/mail/delete")
    public void deleteMail(@RequestParam String id, @RequestParam String account){
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

