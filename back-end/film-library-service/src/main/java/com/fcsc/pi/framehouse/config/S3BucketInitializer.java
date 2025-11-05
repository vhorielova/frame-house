package com.fcsc.pi.framehouse.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.BucketAlreadyExistsException;
import software.amazon.awssdk.services.s3.model.BucketAlreadyOwnedByYouException;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;

@Component
public class S3BucketInitializer implements ApplicationRunner {

    final static Logger logger = LoggerFactory.getLogger(S3BucketInitializer.class);

    final private S3Client s3Client;
    final private String bucketName;

    @Autowired
    S3BucketInitializer(
            S3Client s3Client,
            @Value("${s3.bucket.name}") String bucketName) {
        this.s3Client = s3Client;
        this.bucketName = bucketName;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {
            s3Client.createBucket(CreateBucketRequest.builder().bucket(bucketName).build());
            logger.info("New bucket '{}' have been created.", bucketName);
        }
        catch (BucketAlreadyExistsException | BucketAlreadyOwnedByYouException e) {
            logger.info("Bucket {} already exists. No need to create a new one", bucketName);
        }
    }
}
