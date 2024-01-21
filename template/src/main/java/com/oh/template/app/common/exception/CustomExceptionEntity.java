package com.oh.template.app.common.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@Builder
public record CustomExceptionEntity(
        String result,
        String message
) {
}
