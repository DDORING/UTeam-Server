package com.api.core.domain.dopamineTime.service;

import com.api.core.domain.dopamineTime.dto.DopamineTimeReq;
import com.api.core.domain.dopamineTime.dto.DopamineTimeRes;
import com.api.core.domain.dopamineTime.entity.DopamineTime;
import com.api.core.domain.dopamineTime.repository.DopamineTimeRepository;
import com.api.core.global.oauth2.service.AuthService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DopamineTimeService {
    private final AuthService authService;
    private final DopamineTimeRepository dopamineTimeRepository;

    @Transactional
    public DopamineTimeRes startDopamineTime(){
        DopamineTime dopamineTime = new DopamineTime(authService.getMember(), false, false, null);

        DopamineTime startedDopTime = dopamineTimeRepository.save(dopamineTime);
        return DopamineTimeRes.of(startedDopTime);
    }
}
