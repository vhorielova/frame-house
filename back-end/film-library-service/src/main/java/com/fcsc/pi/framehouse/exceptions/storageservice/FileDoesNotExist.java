package com.fcsc.pi.framehouse.exceptions.storageservice;

public class FileDoesNotExist extends RuntimeException {
    public FileDoesNotExist(String message) {
        super(message);
    }
}
