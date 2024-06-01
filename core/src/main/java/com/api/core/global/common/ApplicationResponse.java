package com.api.core.global.common;

import com.api.core.global.error.ErrorCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApplicationResponse<T>(
        LocalDateTime timestamp,
        Integer code,
        String message,
        T data
) {

    public static <T> ApplicationResponse<T> ok(T data) {
        return ApplicationResponse.<T>builder()
                .timestamp(LocalDateTime.now())
                .code(ErrorCode.SUCCESS.getCode())
                .message(ErrorCode.SUCCESS.getMessage())
                .data(data)
                .build();
    }

    public static <T> ApplicationResponse<T> ok() {
        return ApplicationResponse.<T>builder()
                .timestamp(LocalDateTime.now())
                .code(ErrorCode.SUCCESS.getCode())
                .message(ErrorCode.SUCCESS.getMessage())
                .build();
    }
}
