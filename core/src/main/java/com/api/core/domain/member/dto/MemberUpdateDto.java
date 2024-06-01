package com.api.core.domain.member.dto;

import com.api.core.domain.member.entity.Character;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Schema(description = "멤버 업데이트 포맷")
public class MemberUpdateDto {
    @Schema(description = "변경할 닉네임")
    private String nickname;

    @Schema(description = "선택한 캐릭터")
    private Character character;

    public MemberServiceDto toServiceDto(String email) {
        return MemberServiceDto.builder()
                .email(email)
                .nickname(this.nickname)
                .character(this.character)
                .build();
    }

}
