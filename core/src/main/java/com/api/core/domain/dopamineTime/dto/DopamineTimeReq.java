package com.api.core.domain.dopamineTime.dto;

import com.api.core.domain.dopamineTime.entity.DopamineTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;

public record DopamineTimeReq(
        @Schema(
                description = "도파민 충전 시간 연장 여부 관리"
        )
        Boolean isExtended,
        @Schema(
                description = "도파민 충전 시간 중지 여부 관리"
        )
        Boolean isStoped,

        @Schema(
                description = "도파민 충전 시간 종료 여부 관리"
        )
        Boolean isFinished,

        @Schema(
                description = "도파민 충전 시간 종료 시간 관리"
        )
        LocalDateTime endTime
) {

    @Builder
    public DopamineTime from(){
        return DopamineTime.builder()
                .isExtended(isExtended)
                .isFinished(isFinished)
                .isStoped(isStoped)
                .endTime(endTime)
                .build();
    }

}
