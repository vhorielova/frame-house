package com.fcsc.pi.framehouse.exceptions.storageservice;

public class FileAlreadyExistsException extends IllegalArgumentException {
    public FileAlreadyExistsException(String fileKey) {
        super("The file with key key '" + fileKey + "' is already taken.");
    }
}
