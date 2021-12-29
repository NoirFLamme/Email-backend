package com.example.email.files;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import com.example.email.objects.Attachment;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FilesStorageService {
    public void init();

    public void save(MultipartFile file, int id, String email);

    public List<Attachment> load(int id);

    public void deleteAll();

    public Stream<Path> loadAll();

}