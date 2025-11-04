package com.fcsc.pi.framehouse.service;

import com.fcsc.pi.framehouse.exceptions.storageservice.FileAlreadyExistsException;
import com.fcsc.pi.framehouse.exceptions.storageservice.FileDoesNotExist;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IStorageService {

    void save(MultipartFile file) throws IOException, FileAlreadyExistsException;

    void delete(String filename) throws FileDoesNotExist;

    boolean doesFileExist(String filename);

    MultipartFile load(String filename);
}
