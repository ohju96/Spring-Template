package com.oh.template.app.common.query.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import static com.oh.template.app.common.query.response.ResponseEnum.SUCCESS;
import static lombok.AccessLevel.PROTECTED;

public class CustomResponseUtils {

public static <T> CustomResponse<T> success(T data) {
        return new CustomResponse<>(SUCCESS.getResult(), SUCCESS.getMessage(), data);
    }

    @Getter
    @ToString
    @AllArgsConstructor(access = PROTECTED)
    public static class CustomResponse <T> {
        private final String result;
        private final String message;
        private final T data;
    }
}
