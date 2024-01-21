package com.oh.template.app.common.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    private final ExceptionEnum exception;

    public CustomException(ExceptionEnum ex) {
        super(ex.getMessage());
        this.exception = ex;
    }
}
