package com.taptica.rotemwald.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Empty person details exception
 */
@ResponseStatus(value=HttpStatus.NOT_ACCEPTABLE)
public class EmptyPersonDetailsException extends Exception {

    public EmptyPersonDetailsException(String message) {
        super(message);
    }
}
