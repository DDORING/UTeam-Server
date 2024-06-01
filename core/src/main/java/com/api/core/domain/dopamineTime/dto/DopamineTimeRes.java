package com.api.core.domain.dopamineTime.dto;

import com.api.core.domain.dopamineTime.entity.DopamineTime;
import com.api.core.domain.member.entity.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record DopamineTimeRes (
        @Schema(
                description = "도파민 충전 시간 PK 관리"
        )
        Long id,
        @Schema(
                description = "사용자 관리"
        )
        Member member,
        @Schema(
            description = "도파민 충전 시간 연장 여부 관리"
        )
       Boolean isExtended,
       @Schema(
               description = "도파민 충전 시간 종료 여부 관리"
       )
       Boolean isFinished,

       @Schema(
               description = "도파민 충전 시간 종료 시간 관리"
       )
       LocalDateTime endTime
) {
    public static DopamineTimeRes of(DopamineTime dopamineTime){
        return DopamineTimeRes.builder()
                .id(dopamineTime.getDoptime_id())
                .member(dopamineTime.getMember())
                .isExtended(dopamineTime.isExtended())
                .isFinished(dopamineTime.isFinished())
                .endTime(dopamineTime.getEndTime())
                .build();
    }

}
