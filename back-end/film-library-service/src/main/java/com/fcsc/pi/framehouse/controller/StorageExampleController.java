package com.fcsc.pi.framehouse.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("storage-example")
@RestController()
public class StorageExampleController {

    @GetMapping
    public String storageExample(){
        return "storage-example";
    }
}
