package com.api.core.domain.notification.controller;

import com.api.core.domain.notification.dto.FcmSendDto;
import com.api.core.domain.notification.service.FcmService;
import com.google.firebase.messaging.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/api/v1/fcm")
public class FcmController {

    private final FcmService fcmService;

    public FcmController(FcmService fcmService) {
        this.fcmService = fcmService;
    }

    @PostMapping("/send")
    public ResponseEntity<HttpStatus> pushMessage(@RequestBody @Validated FcmSendDto fcmSendDto) throws IOException {
        log.debug("[+] 푸시 메시지를 전송합니다. ");
        int result = fcmService.sendMessageTo(fcmSendDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
