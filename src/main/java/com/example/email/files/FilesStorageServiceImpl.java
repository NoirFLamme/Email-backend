package com.example.email.files;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.example.email.AccountsRepo;
import com.example.email.EmailService;
import com.example.email.objects.Account;
import com.example.email.objects.Attachment;
import com.example.email.objects.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import static com.example.email.EmailService.accountCont;

@Service
public class FilesStorageServiceImpl implements FilesStorageService {

    private final Path root = Paths.get("uploads");

    @Autowired
    private AttachmentRepo attachments;

    @Override
    public void init() {
        try {
            Files.createDirectory(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public void save(MultipartFile file, int id, String email) {
        try {
            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));

            Attachment attachment = new Attachment(file.getOriginalFilename(),String.valueOf(this.root.resolve(file.getOriginalFilename())), id);
            attachments.insert(attachment);
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    @Override
    public List<Attachment> load(int id) {

        List<Attachment> attachmentList = attachments.findAllByValue(id);

        return attachmentList;
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }
}