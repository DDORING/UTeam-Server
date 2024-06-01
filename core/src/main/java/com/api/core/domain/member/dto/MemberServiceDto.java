package com.api.core.domain.member.dto;

import com.api.core.domain.member.entity.Character;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MemberServiceDto {
    private String email;
    private String nickname;
    private Character character;
    private String imageUrl;
}
