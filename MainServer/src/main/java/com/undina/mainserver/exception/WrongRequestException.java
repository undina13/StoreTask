package com.undina.mainserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class WrongRequestException extends RuntimeException {
    public WrongRequestException(String message) {
        super(message);
    }
}
