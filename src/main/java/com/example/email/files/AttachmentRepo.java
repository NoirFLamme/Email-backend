package com.example.email.files;

import com.example.email.objects.Attachment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AttachmentRepo extends MongoRepository<Attachment, String> {
    List<Attachment> findAllByValue(int id);
}
