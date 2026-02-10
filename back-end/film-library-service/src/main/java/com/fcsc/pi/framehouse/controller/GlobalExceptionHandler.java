package com.fcsc.pi.framehouse.controller;

import com.fcsc.pi.framehouse.dto.GeneralErrorResponse;
import com.fcsc.pi.framehouse.exceptions.ResourceNotFound;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;


@RestControllerAdvice
public class GlobalExceptionHandler {

    final private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ResourceNotFound.class)
    ResponseEntity<GeneralErrorResponse> handleResourceNotFound(ResourceNotFound exception) {
        GeneralErrorResponse error = new GeneralErrorResponse(
                "not_found",
                "The " + exception.getResourceName() + " is not found"
        );
        logger.trace(exception.getMessage());
        logger.debug(Arrays.toString(exception.getStackTrace()));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
