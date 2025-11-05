package com.fcsc.pi.framehouse.controller;

import com.fcsc.pi.framehouse.exceptions.storageservice.FileAlreadyExistsException;
import com.fcsc.pi.framehouse.service.IStorageService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.model.HeadObjectResponse;

import java.io.IOException;

@RestController()
@RequestMapping("/storage-example")
@AllArgsConstructor
public class StorageExampleController {

    final private IStorageService storageService;

    @GetMapping
    public String storageExample() {
        return "storage-example";
    }

    @PostMapping
    public String storageExamplePut(@RequestParam MultipartFile file) {

        try {
            storageService.save(file);
        } catch (IOException e) {
            return "Internal server error";
        } catch (FileAlreadyExistsException e) {
            return "Wrong request: file already exists";
        }

        return "File had been saved";
    }

    @GetMapping("/{filename}")
    public ResponseEntity<Resource> storageExampleGet(@PathVariable String filename) throws IOException {

        Resource file = storageService.load(filename);
        HeadObjectResponse metadata = storageService.loadMetadata(filename);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(metadata.contentType()));
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"");

        return ResponseEntity.ok().headers(headers).body(file);
    }
}
