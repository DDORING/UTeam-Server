package com.api.core.global.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    //1000: 정상
    SUCCESS(HttpStatus.OK, 1000, "정상적인 요청입니다."),

    //2000: 오류
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 2000, "내부 서버에 오류가 발생했습니다."),

    //3000: Member
    MEMBER_NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, 3000, "회원 정보를 찾지 못했습니다." ),

    //4000: DopamineTime
    ALREADY_CREATE_DOPAMINETIME(HttpStatus.BAD_REQUEST, 4000, "이미 하루 도파민 충전 시간을 실행했습니다."),
    ALREADY_PASSED_DOPAMINETIME(HttpStatus.BAD_REQUEST, 4001, "이미 하루 도파민 충전 시간을 사용했습니다."),
    NOT_FOUND_DOPAMINETIME(HttpStatus.NOT_FOUND, 4002, "도파민 충전 시간을 찾을 수 없습니다.");


    private final HttpStatus status;
    private final Integer code;
    private final String message;
}
