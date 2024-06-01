package com.api.core.domain.notification.controller;

import com.api.core.domain.notification.dto.FcmSendDto;
import com.api.core.domain.notification.service.FcmService;
import com.api.core.global.common.ApplicationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("fcm")
public class FcmController {

    private final FcmService fcmService;

    public FcmController(FcmService fcmService) {
        this.fcmService = fcmService;
    }

    @PostMapping("")
    public ApplicationResponse<?> pushMessage(@RequestBody @Validated FcmSendDto fcmSendDto) throws IOException {
        return ApplicationResponse.ok(fcmService.sendMessageTo(fcmSendDto));
    }

}
