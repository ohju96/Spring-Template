package com.oh.template.app.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
@AllArgsConstructor
public enum ExceptionEnum {

    /**
     * 0000 ~ 0999 : Basic Exception
     */
    ACCESS_DENIED(HttpStatus.FORBIDDEN, "code-0001", "access denied"),
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "code-0002", "invalid parameter"),
    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "code-0003", "invalid request"),
    INVALID_RESPONSE(HttpStatus.INTERNAL_SERVER_ERROR, "code-0004", "invalid response"),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "code-0005", "invalid token"),
    NOT_FOUND(HttpStatus.NOT_FOUND, "code-0006", "not found"),
    UNEXPECTED_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "code-0007", "unexpected error"),
    MISSING_BODY(HttpStatus.BAD_REQUEST, "code-0008", "missing body"),
    RUNTIME_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "code-0009", "runtime exception"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "code-0010", "bad request"),
    ;

    private final HttpStatus status;
    private final String result;
    private final String message;
}
