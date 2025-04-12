package com.example.tms.services;

import com.example.tms.Response;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileStorageService {

    List<String> listFiles();
    Response<String> uploadFile(int taskId, MultipartFile file);
     Resource getFile(String filename);
}
