package com.oh.template.app.common.query.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum ResponseEnum {

    SUCCESS("code-0000", "success")
    ;

    private final String result;
    private final String message;
}
