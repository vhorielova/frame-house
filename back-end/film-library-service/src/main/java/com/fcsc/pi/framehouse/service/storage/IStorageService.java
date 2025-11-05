package com.fcsc.pi.framehouse.service.storage;

import com.fcsc.pi.framehouse.exceptions.storageservice.FileAlreadyExistsException;
import com.fcsc.pi.framehouse.exceptions.storageservice.FileDoesNotExistException;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.model.HeadObjectResponse;

import java.io.IOException;

public interface IStorageService {

    void save(MultipartFile file) throws IOException, FileAlreadyExistsException;

    void delete(String filename) throws FileDoesNotExistException;


    boolean doesFileExist(String filename);

    HeadObjectResponse loadMetadata(String filename) throws FileDoesNotExistException;

    Resource load(String filename) throws IOException, FileDoesNotExistException;
}
