package com.taptica.rotemwald.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Person does not exist exception
 */
@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class PersonDoesNotExistException extends Exception {

    public PersonDoesNotExistException(String message) {
        super(message);
    }
}
