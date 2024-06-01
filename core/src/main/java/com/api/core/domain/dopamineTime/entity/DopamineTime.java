package com.api.core.domain.dopamineTime.entity;

import com.api.core.domain.dopamineTime.dto.DopamineTimeReq;
import com.api.core.domain.member.entity.Member;
import com.api.core.global.config.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DopamineTime extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Member.class, fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @Column(name="is_finished", nullable = false, columnDefinition =  "tinyint")
    private boolean isFinished;

    @Column(name="is_extended", nullable = false, columnDefinition =  "tinyint")
    private boolean isExtended;

    @Column(name="is_stoped", nullable = false, columnDefinition =  "tinyint")
    private boolean isStoped;

    @Column(name="total_doptime", nullable = false, columnDefinition = "bigint")
    private Long totalDoptime; //단위:분

    @Column(name = "end_time", nullable = true, columnDefinition = "timestamp")
    private LocalDateTime endTime;

    @Builder
    public DopamineTime(Member member, boolean isExtended, boolean isFinished, boolean isStoped, LocalDateTime startTime, long totalDoptime){
        this.member = member;
        this.isExtended = isExtended;
        this.isFinished = isFinished;
        this.isStoped = isStoped;
        this.totalDoptime = totalDoptime;
        this.endTime = calculateEndTime(startTime, totalDoptime);
    }

    private LocalDateTime calculateEndTime(LocalDateTime startTime, long totalDoptime) {
        return startTime.plus(totalDoptime, ChronoUnit.MINUTES);
    }

    public void stop(DopamineTimeReq req){
        this.isStoped = true;
        this.isFinished = req.isFinished();
        this.isExtended = req.isExtended();
        this.endTime = null;
    }
}
