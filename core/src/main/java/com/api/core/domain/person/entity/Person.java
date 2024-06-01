package com.api.core.domain.person.entity;

import com.api.core.domain.member.entity.Member;
import com.api.core.global.config.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import com.api.core.domain.person.dto.PersonDto;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
@AllArgsConstructor
public class Person extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", unique = true)
    private Member member; // 사용자 ID

    private String name; // 이름
    private String photo; // 사진 URL

    public void update(PersonDto dto) {
        this.name = dto.getName();
        this.photo = dto.getPhoto();
    }
}
