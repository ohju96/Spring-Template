package com.oh.template.app.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import static com.oh.template.app.common.exception.ExceptionEnum.*;

@Slf4j
@RestControllerAdvice
public class CustomExceptionAdvice {

    @ExceptionHandler({CustomException.class})
    public ResponseEntity<CustomExceptionEntity> exceptionHandler (
            final CustomException ex
    ) {
        log.error(ex.toString());
        return ResponseEntity
                .status(ex.getException().getStatus())
                .body(CustomExceptionEntity.builder()
                        .result(ex.getException().getResult())
                        .message(ex.getException().getMessage())
                        .build());
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<CustomExceptionEntity> exceptionHandler (
            final RuntimeException ex
    ) {
        log.error(ex.toString());
        return ResponseEntity
                .status(RUNTIME_EXCEPTION.getStatus())
                .body(CustomExceptionEntity.builder()
                        .result(RUNTIME_EXCEPTION.getResult())
                        .message(RUNTIME_EXCEPTION.getMessage())
                        .build());
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<CustomExceptionEntity> exceptionHandler (
            final AccessDeniedException ex
    ) {
        log.error(ex.toString());
        return ResponseEntity
                .status(ACCESS_DENIED.getStatus())
                .body(CustomExceptionEntity.builder()
                        .result(ACCESS_DENIED.getResult())
                        .message(ACCESS_DENIED.getMessage())
                        .build());
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<CustomExceptionEntity> exceptionHandler (
            final Exception ex
    ) {
        log.error(ex.toString());
        return ResponseEntity
                .status(ACCESS_DENIED.getStatus())
                .body(CustomExceptionEntity.builder()
                        .result(ACCESS_DENIED.getResult())
                        .message(ex.getMessage())
                        .build());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<CustomExceptionEntity> exceptionHandler (
            final MethodArgumentNotValidException ex
    ) {

        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        log.error(errors.toString());

        return ResponseEntity
                .status(BAD_REQUEST.getStatus())
                .body(CustomExceptionEntity.builder()
                        .result(BAD_REQUEST.getResult())
                        .message(errors.toString())
                        .build());
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<CustomExceptionEntity> exceptionHandler (
            final HttpMessageNotReadableException ex
    ) {
        log.error(ex.toString());
        return ResponseEntity
                .status(MISSING_BODY.getStatus())
                .body(CustomExceptionEntity.builder()
                        .result(MISSING_BODY.getResult())
                        .message(MISSING_BODY.getMessage())
                        .build());
    }
}
