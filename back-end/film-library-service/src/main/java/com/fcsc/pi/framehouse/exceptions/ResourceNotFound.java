package com.fcsc.pi.framehouse.exceptions;

public class ResourceNotFound extends RuntimeException {
    private String resourceName;
    public ResourceNotFound(String resourceName) {
        super("Resource '" + resourceName + "' is not found");
        this.resourceName = resourceName;
    }

    public String getResourceName() {
        return resourceName;
    }


}
