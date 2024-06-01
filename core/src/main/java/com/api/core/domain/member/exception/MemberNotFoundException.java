package com.api.core.domain.member.exception;

import com.api.core.global.error.ErrorCode;
import com.api.core.global.error.exception.BusinessException;

public class MemberNotFoundException extends BusinessException {
    public MemberNotFoundException() {
        super(ErrorCode.MEMBER_NOT_FOUND_ERROR);
    }
}