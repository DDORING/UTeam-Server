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
        if(dopamineTimeRepository.existsDopamineTimeByMember(member)) {//이미 테이블이 존재할때
            if(dopamineTimeRepository.existsDopamineTimeByMemberAndIsFinishedTrue(member))//이미 사용O
            throw new BusinessException(ErrorCode.ALREADY_CREATE_DOPAMINETIME);
        };

        DopamineTime dopamineTime = new DopamineTime(member, false, false, false, LocalDateTime.now(), req.totalDopTime(), 0 );

        DopamineTime startedDopTime = dopamineTimeRepository.save(dopamineTime);
        return DopamineTimeRes.of(startedDopTime);
    }

    @Transactional
    public DopamineTimeRes modifyDopamineTime(Long id, DopamineTimeReq req){
        if(dopamineTimeRepository.existsDopamineTimeByIdAndIsFinishedTrue(id)) {
            throw new BusinessException(ErrorCode.ALREADY_PASSED_DOPAMINETIME);
        }

        Member member = authService.getMember();

        DopamineTime dopamineTime = dopamineTimeRepository.findByIdAndMember(id, member);
        if(req.isStoped()) dopamineTime.restart(req);
        else dopamineTime.stop(req);

        return DopamineTimeRes.of(dopamineTime);
    }

    @Transactional
    public DopamineTimeRes extendDopamineTime(Long id, DopamineTimeReq req){
        if(dopamineTimeRepository.existsDopamineTimeByIdAndIsFinishedTrue(id)) {
            throw new BusinessException(ErrorCode.ALREADY_PASSED_DOPAMINETIME);
        }
        Member member = authService.getMember();

        DopamineTime dopamineTime = dopamineTimeRepository.findByIdAndMember(id, member);

        dopamineTime.extend(req);
        return DopamineTimeRes.of(dopamineTime);
    }
}
