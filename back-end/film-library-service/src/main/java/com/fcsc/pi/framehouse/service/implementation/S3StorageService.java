package com.fcsc.pi.framehouse.service.implementation;

import com.fcsc.pi.framehouse.exceptions.storageservice.FileAlreadyExistsException;
import com.fcsc.pi.framehouse.exceptions.storageservice.FileDoesNotExistException;
import com.fcsc.pi.framehouse.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Objects;

import static java.lang.Math.min;

@Service
public class S3StorageService implements StorageService {

    final private S3Client s3Client;
    final private String bucketName;
    final private String postersFolder;

    @Autowired
    public S3StorageService(S3Client s3Client,
                     @Value("${s3.bucket.name}") String bucketName,
                     @Value("${s3.posters-folder-name}") String postersFolder
    ) {
        this.s3Client = s3Client;
        this.bucketName = bucketName;
        this.postersFolder = postersFolder;
    }

    // -------------
    // Helper private methods
    // ------------

    // Gets filename (without path) from the given MultiparFile
    static private String extractOnlyFileName(MultipartFile file) {
        String fileName = Path.of(
                Objects.requireNonNull(
                        file.getOriginalFilename())
        ).getFileName().toString();

        if (fileName.isEmpty()) {
            throw new IllegalArgumentException("Filename is empty: " + fileName);
        }

        return fileName;
    }

    /**
     * Generates taking up to tree first words from the name.
     * If such film exists, increments suffix, e.g. nice_film_1, nice_film_2 ect
    */
    private String generateFilename(String name) {

        if (name.isEmpty()) {
            throw new IllegalArgumentException("Filename is empty: " + name);
        }

        // Building the initial name with up to 3 words
        final String LETTER_DIGITS_REGEX = "[^\\p{L}\\p{N}]";
        String nameOnlyAllowedChars = name.replaceAll(LETTER_DIGITS_REGEX, "");
        String[] words = nameOnlyAllowedChars.split(" ");
        if (words.length == 0) {
            words = new String[]{"film"};
        }
        StringBuilder initialFilenameBuilder = new StringBuilder();
        for (int i = 0; i < min(words.length, 3); i++) {
            initialFilenameBuilder.append(words[i]);
        }

        // Checking available names
        String filename = initialFilenameBuilder.toString();
        int i = 0;
        while (doesFileExist(filename)) {
            filename = initialFilenameBuilder + "_" + i;
            i++;
        }

        return filename;
    }

    private String withPosterPath(String filename) {
        return postersFolder + "/" + filename;
    }

    public String extractExtension(String fileName) {
        if (fileName != null && fileName.contains(".")) {
            return fileName.substring(fileName.lastIndexOf("."));
        }
        return "";
    }

    // -------------
    // StorageService implementation methods
    // ------------

    @Override
    public boolean doesFileExist(String filename) {
        try {
            loadMetadata(filename);
            return true;
        } catch (FileDoesNotExistException e) {
            return false;
        }
    }

    @Override
    public void save(MultipartFile file) throws IOException, FileAlreadyExistsException {

        if (doesFileExist(file.getOriginalFilename())) {
            throw new FileAlreadyExistsException(file.getOriginalFilename());
        }

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(withPosterPath(extractOnlyFileName(file)))
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
    public String generateNameAndSave(MultipartFile file, String filmName) throws IOException, FileAlreadyExistsException {

        String fileName = generateFilename(filmName) + extractExtension(file.getOriginalFilename());

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(withPosterPath(fileName))
                .contentType(file.getContentType())
                .contentLength(file.getSize())
                .build();

        RequestBody requestBody = RequestBody.fromInputStream(
                file.getInputStream(),
                file.getSize()
        );

        s3Client.putObject(putObjectRequest, requestBody);

        return fileName;
    }

    @Override
    public void delete(String filename) {

        if (!doesFileExist(filename)) {
            throw new FileDoesNotExistException(filename);
        }

        s3Client.deleteObject(DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(withPosterPath(filename))
                .build());
    }

    @Override
    public HeadObjectResponse loadMetadata(String filename) throws FileDoesNotExistException {
        try {
            return s3Client.headObject(HeadObjectRequest.builder()
                    .bucket(bucketName)
                    .key(withPosterPath(filename))
                    .build());

        } catch (NoSuchKeyException e) {
            throw new FileDoesNotExistException(filename);
        }
    }

    @Override
    public Resource load(String filename) throws IOException {
        InputStream response = s3Client.getObject(
                GetObjectRequest.builder()
                .bucket(bucketName)
                .key(withPosterPath(filename))
                .build());
        return new InputStreamResource(response);
    }
}
