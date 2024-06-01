package com.api.core.domain.member.service;

import com.api.core.domain.member.dto.MemberServiceDto;
import com.api.core.domain.member.dto.response.MemberResponse;
import com.api.core.domain.member.entity.Member;
import com.api.core.domain.member.exception.MemberNotFoundException;
import com.api.core.domain.member.repository.MemberRepository;
import com.api.core.global.oauth2.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final AuthService authService;
    private final MemberRepository memberRepository;


    @Transactional(readOnly = true)
    public boolean isDuplicatedNickname(MemberServiceDto memberServiceDto) {
        return memberRepository.existsMemberByNickname(memberServiceDto.getNickname());
    }

    @Transactional
    public void update(MemberServiceDto memberServiceDto) {
        findByEmail(memberServiceDto.getEmail()).update(memberServiceDto);
    }

    @Transactional(readOnly = true)
    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new);
    }

    public MemberResponse getMemberInfo() {
        Member member = authService.getMember();
        return MemberResponse.builder()
                .email(member.getEmail())
                .nickname(member.getNickname())
                .build();
    }
}

