package com.undina.mainserver.controller;

import com.undina.mainserver.dto.ApiError;
import com.undina.mainserver.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionHandlers {
    static final String REASON_MESSAGE = "object not found";

    @ExceptionHandler(ObjectNotFoundException.class)
    public ApiError notFound(RuntimeException e) {
        return ApiError.builder()
                .status(String.valueOf(HttpStatus.NOT_FOUND))
                .reason(REASON_MESSAGE)
                .message(e.getLocalizedMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(OrganizationNotFoundException.class)
    public ApiError notFoundOrganization(RuntimeException e) {
        return ApiError.builder()
                .status(String.valueOf(HttpStatus.NOT_FOUND))
                .reason(REASON_MESSAGE)
                .message(e.getLocalizedMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ApiError notFoundProduct(RuntimeException e) {
        return ApiError.builder()
                .status(String.valueOf(HttpStatus.NOT_FOUND))
                .reason(REASON_MESSAGE)
                .message(e.getLocalizedMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(UserNotOwnerException.class)
    public ApiError forbidden(RuntimeException e) {
        return ApiError.builder()
                .status(String.valueOf(HttpStatus.FORBIDDEN))
                .reason(REASON_MESSAGE)
                .message(e.getLocalizedMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(WrongRequestException.class)
    public ApiError forbiddenWrongRequestException(RuntimeException e) {
        return ApiError.builder()
                .status(String.valueOf(HttpStatus.FORBIDDEN))
                .reason(REASON_MESSAGE)
                .message(e.getLocalizedMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }
}
