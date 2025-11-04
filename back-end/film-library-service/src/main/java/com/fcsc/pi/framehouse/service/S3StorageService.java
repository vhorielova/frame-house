package com.fcsc.pi.framehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;

@Service
public class S3StorageService implements IStorageService {

    final private S3Client s3Client;
    final private String bucketName;
    final private String postersFolder;

    @Autowired
    S3StorageService(S3Client s3Client,
                     @Value("${s3.bucket.name}") String bucketName,
                     @Value("${s3.posters-folder-name}") String postersFolder
    ) {
        this.s3Client = s3Client;
        this.bucketName = bucketName;
        this.postersFolder = postersFolder;
    }

    private String withPosterPath(String filename) {
        return postersFolder + "/" + filename;
    }

    @Override
    public void save(MultipartFile file) throws IOException {

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(withPosterPath(file.getName()))
                .contentType(file.getContentType())
                .contentLength(file.getSize())
                .build();

        RequestBody requestBody = RequestBody.fromInputStream(
                file.getInputStream(),
                file.getSize()
        );

        s3Client.putObject(putObjectRequest, requestBody);
    }

    @Override
    public void delete(String filename) {
        s3Client.deleteObject(DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(withPosterPath(filename))
                .build());
    }

    @Override
    public MultipartFile load(String filename) {

        s3Client.getObject(GetObjectRequest.builder()
                .bucket(bucketName)
                .key(withPosterPath(filename))
                .build());

        return null;
    }
}
