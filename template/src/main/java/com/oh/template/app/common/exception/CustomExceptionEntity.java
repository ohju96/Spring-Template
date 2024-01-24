package com.oh.template.app.common.exception;

import lombok.Builder;

@Builder
public record CustomExceptionEntity(
        String result,
        String message
) {
}
