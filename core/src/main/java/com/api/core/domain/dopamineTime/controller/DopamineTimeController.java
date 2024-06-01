package com.api.core.domain.dopamineTime.controller;

import com.api.core.domain.dopamineTime.dto.DopamineTimeReq;
import com.api.core.domain.dopamineTime.dto.DopamineTimeRes;
import com.api.core.domain.dopamineTime.service.DopamineTimeService;
import com.api.core.global.common.ApplicationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("dopamine-time")
@RequiredArgsConstructor
@Tag(name = "DopamineTime", description = "도파민 충전 시간 관련 API")
public class DopamineTimeController {
    private final DopamineTimeService dopamineTimeService;

    @PostMapping("")
    @Operation(summary = "도파민 충전 시간 실행 API", description = "도파민 충전 시간 실행 버튼 시 생성")
    public ApplicationResponse<DopamineTimeRes> startDopamineTime(){
        return ApplicationResponse.ok(dopamineTimeService.startDopamineTime());
    }
    /*

    @GetMapping("/{id}")
    @Operation(summary = "도파민 충전 시간 중지 API", description = "도파민 충전 시간 중지 버튼 시 요청")
    public ApplicationResponse<DopamineTimeRes> stopDopamineTime(){
        return ApplicationResponse.ok(dopamineTimeService.stopDopamineTime());
    }
    
     */

}
