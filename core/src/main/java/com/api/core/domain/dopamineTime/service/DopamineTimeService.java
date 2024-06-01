package com.api.core.domain.dopamineTime.service;

import com.api.core.domain.dopamineTime.dto.DopamineTimeReq;
import com.api.core.domain.dopamineTime.dto.DopamineTimeRes;
import com.api.core.domain.dopamineTime.entity.DopamineTime;
import com.api.core.domain.dopamineTime.repository.DopamineTimeRepository;
import com.api.core.domain.member.entity.Member;
import com.api.core.global.error.ErrorCode;
import com.api.core.global.error.exception.BusinessException;
import com.api.core.global.oauth2.service.AuthService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class DopamineTimeService {
    private final AuthService authService;
    private final DopamineTimeRepository dopamineTimeRepository;

    @Transactional
    public DopamineTimeRes startDopamineTime(DopamineTimeReq req){
        Member member = authService.getMember();

        if(!dopamineTimeRepository.existsDopamineTimeByMemberAndIsFinishedFalse(member)
                && dopamineTimeRepository.existsDopamineTimeByMember(member)){
            throw new BusinessException(ErrorCode.ALREADY_CREATE_DOPAMINETIME);
        };

        DopamineTime dopamineTime = new DopamineTime(member, false, false, false, LocalDateTime.now(), req.totalDopTime());

        DopamineTime startedDopTime = dopamineTimeRepository.save(dopamineTime);
        return DopamineTimeRes.of(startedDopTime);
    }

    @Transactional
    public DopamineTimeRes stopDopamineTime(Long id, DopamineTimeReq req){
        if(!dopamineTimeRepository.existsDopamineTimeByIdAndIsFinishedFalse(id)) {
            throw new BusinessException(ErrorCode.ALREADY_PASSED_DOPAMINETIME);
        }


        Member member = authService.getMember();

        DopamineTime dopamineTime = dopamineTimeRepository.findByIdAndMember(id, member);

        dopamineTime.stop(req);
        return DopamineTimeRes.of(dopamineTime);
    }
}
