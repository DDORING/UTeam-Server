package com.api.core.global.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    SUCCESS(HttpStatus.OK, "정상적인 요청입니다."),

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "내부 서버에 오류가 발생했습니다."),

    // MEMBER
    MEMBER_NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, "회원 정보를 찾지 못했습니다." );


    private final HttpStatus status;
    private final String message;
}
