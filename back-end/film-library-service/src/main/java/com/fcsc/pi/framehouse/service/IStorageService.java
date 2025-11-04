package com.fcsc.pi.framehouse.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IStorageService {

    void save(MultipartFile file) throws IOException;

    void delete(String filename);

    MultipartFile load(String filename);
}
