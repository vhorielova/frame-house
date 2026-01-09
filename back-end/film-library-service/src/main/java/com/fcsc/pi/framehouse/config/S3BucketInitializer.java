package com.fcsc.pi.framehouse.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.BucketAlreadyExistsException;
import software.amazon.awssdk.services.s3.model.BucketAlreadyOwnedByYouException;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.PutBucketPolicyRequest;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class S3BucketInitializer implements ApplicationRunner {

    final static Logger logger = LoggerFactory.getLogger(S3BucketInitializer.class);

    final private S3Client s3Client;
    final private String bucketName;
    final private String publicBucketPolicyJsonTemplate;

    @Autowired
    S3BucketInitializer(
            S3Client s3Client,
            @Value("${s3.bucket.name}") String bucketName,
            @Value("classpath:aws/bucket-public-policy.json") Resource publicBucketPolicyResource
    ) {
        this.s3Client = s3Client;
        this.bucketName = bucketName;
        // Loading public bucket policy json template
        try {
            this.publicBucketPolicyJsonTemplate = StreamUtils.copyToString(
                    publicBucketPolicyResource.getInputStream(),
                    StandardCharsets.UTF_8
            );
        } catch (IOException e) {
            throw new RuntimeException("Could not read bucket policy resource", e);
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ensureBucketCreated();
        makeBucketPublic();
    }

    private boolean ensureBucketCreated() {
        try {
            s3Client.createBucket(CreateBucketRequest.builder().bucket(bucketName).build());
            logger.info("New bucket '{}' have been created.", bucketName);
            return true;
        } catch (BucketAlreadyExistsException | BucketAlreadyOwnedByYouException e) {
            logger.info("Bucket {} already exists. No need to create a new one", bucketName);
            return false;
        }
    }

    // Makes the bucket public
    // If bucket is already public, no worries, no duplicated politics will be created.
    private void makeBucketPublic() {

        String finalPolicyJson = publicBucketPolicyJsonTemplate.formatted(bucketName);

        try {
            PutBucketPolicyRequest policyRequest = PutBucketPolicyRequest.builder()
                    .bucket(bucketName)
                    .policy(finalPolicyJson)
                    .build();

            s3Client.putBucketPolicy(policyRequest);

            logger.info("Added public read policy to the bucket {}.", bucketName);

        } catch (Exception e) {
            throw new RuntimeException("Failed to set bucket policy", e);
        }
    }
}
