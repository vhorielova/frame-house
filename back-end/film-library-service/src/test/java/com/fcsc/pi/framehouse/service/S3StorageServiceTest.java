package com.fcsc.pi.framehouse.service;

import com.fcsc.pi.framehouse.exceptions.storageservice.FileAlreadyExistsException;
import com.fcsc.pi.framehouse.exceptions.storageservice.FileDoesNotExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class S3StorageServiceTest {

    private S3Client s3Client;
    private String bucketName = "framehouse-test";

    @Value("${s3.posters-folder-name}")
    private String folder;
    public S3StorageService s3StorageService;

    @BeforeEach
    void setUp() {
        s3Client = Mockito.mock(S3Client.class);
        s3StorageService = new S3StorageService(s3Client, bucketName, folder);
    }

    @Test
    void saveCorrect() {
        when(
                s3Client.headObject(any(HeadObjectRequest.class))
        ).thenThrow(NoSuchKeyException.class);

        MultipartFile file = new MockMultipartFile("file", "file".getBytes());

        assertDoesNotThrow(() -> {
            s3StorageService.save(file);
        });
    }

    @Test
    void saveExisting_ShouldThrowException() {
        MultipartFile file = new MockMultipartFile("file", "file".getBytes());
        assertThrows(FileAlreadyExistsException.class,() -> s3StorageService.save(file));
    }

    @Test
    void deleteExisting() {

        assertDoesNotThrow(() -> {
            s3StorageService.delete("file.png");
        });
    }

    @Test
    void deleteNotExisting_ShouldThrowException() {
        when(
                s3Client.headObject(any(HeadObjectRequest.class))
        ).thenThrow(NoSuchKeyException.class);

        assertThrows(FileDoesNotExistException.class, () -> s3StorageService.delete("file.png"));
    }

    @Test
    void load() throws IOException {
        ResponseInputStream<GetObjectResponse> responseMock = Mockito.mock(ResponseInputStream.class);

        when(responseMock.readAllBytes())
                .thenReturn("file".getBytes());

        when(s3Client.getObject(any(GetObjectRequest.class)))
                .thenReturn(responseMock);

        Resource result =  s3StorageService.load("file");
        assertEquals(responseMock.readAllBytes(), result.getInputStream().readAllBytes());
    }
}