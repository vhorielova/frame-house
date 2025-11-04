package com.fcsc.pi.framehouse.service;

import com.fcsc.pi.framehouse.exceptions.storageservice.FileAlreadyExistsException;
import com.fcsc.pi.framehouse.exceptions.storageservice.FileDoesNotExistException;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IStorageService {

    void save(MultipartFile file) throws IOException, FileAlreadyExistsException;

    void delete(String filename) throws FileDoesNotExistException;

    boolean doesFileExist(String filename);

    Resource load(String filename) throws IOException;
}
