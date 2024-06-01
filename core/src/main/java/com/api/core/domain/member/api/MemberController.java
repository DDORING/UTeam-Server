package com.api.core.domain.member.api;

import com.api.core.domain.member.dto.MemberServiceDto;
import com.api.core.domain.member.dto.MemberUpdateDto;
import com.api.core.domain.member.dto.response.MemberResponse;
import com.api.core.domain.member.service.MemberService;
import com.api.core.global.annotation.LoginMemberEmail;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("members")
public class MemberController implements MemberApi{
    private final MemberService memberService;

    @PostMapping("")
    public ResponseEntity<HttpStatus> updateMember(@LoginMemberEmail String email,
                                                   @RequestBody MemberUpdateDto memberUpdateDto) {
        MemberServiceDto memberServiceDto = memberUpdateDto.toServiceDto(email);

        if (memberService.isDuplicatedNickname(memberServiceDto)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        memberService.update(memberServiceDto);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    @Override
    public MemberResponse getMemberInfo() {
        return memberService.getMemberInfo();
    }
}

