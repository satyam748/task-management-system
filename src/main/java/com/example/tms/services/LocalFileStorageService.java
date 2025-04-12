package com.example.tms.services;

import ch.qos.logback.core.util.StringUtil;
import com.example.tms.Response;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocalFileStorageService implements FileStorageService{

    @Value("${file.upload-dir}")
    private String uploadDir;

    private Path fileStoragePath;

    @PostConstruct
    public void init(){
        try {
            this.fileStoragePath = Paths.get(uploadDir).toAbsolutePath().normalize();
            Files.createDirectories(this.fileStoragePath);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't read file", e);
        }
    }

    @Override
    public Response<String> uploadFile(int taskId, MultipartFile file) {
        String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
        String filename = "task-" + taskId + "-" + originalFilename;

        try{
            Path targetLocation = this.fileStoragePath.resolve(filename);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return Response.success("File uploaded successfully", filename);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't upload file", e);
        }
    }

    @Override
    public Resource getFile(String filename) {
        try{
            Path filePath = this.fileStoragePath.resolve(StringUtils.cleanPath(filename)).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists())
                return resource;
            else
                throw new RuntimeException("File not found:" + filename);
        } catch (MalformedURLException e) {
            throw new RuntimeException("File not found:" + filename, e);
        }
    }

    @Override
    public List<String> listFiles() {
        try{
            return Files.list(this.fileStoragePath).map(path -> path.getFileName().toString()).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Couldn't list files");
        }
    }
}
