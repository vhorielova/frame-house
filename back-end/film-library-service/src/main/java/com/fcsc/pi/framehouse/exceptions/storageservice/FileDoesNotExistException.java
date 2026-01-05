package com.fcsc.pi.framehouse.exceptions.storageservice;

public class FileDoesNotExistException extends RuntimeException {
    public FileDoesNotExistException(String message) {
        super(message);
    }
}
