package com.undina.mainserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class UserNotOwnerException extends RuntimeException{
    public UserNotOwnerException(String message) {
        super(message);
    }
}
