package com.api.core.domain.dopamineTime.entity;

import com.api.core.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DopamineTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doptime_id;

    @ManyToOne(targetEntity = Member.class, fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @Column(name="is_finished", nullable = false, columnDefinition =  "tinyint")
    private boolean isFinished;

    @Column(name="is_extended", nullable = false, columnDefinition =  "tinyint")
    private boolean isExtended;

    @Column(name="is_stoped", nullable = false, columnDefinition =  "tinyint")
    private boolean isStoped;

    @Column(name = "end_time", nullable = true, columnDefinition = "timestamp")
    private LocalDateTime endTime;

    @Builder
    public DopamineTime(Member member, boolean isExtended, boolean isFinished, boolean isStoped, LocalDateTime endTime){
        this.member = member;
        this.isExtended = isExtended;
        this.isFinished = isFinished;
        this.isStoped = isStoped;
        this.endTime = endTime;
    }
}
