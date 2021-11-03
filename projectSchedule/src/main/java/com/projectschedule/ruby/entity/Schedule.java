package com.projectschedule.ruby.entity;

import com.projectschedule.ruby.entity.enumItem.ProgressStatus;
import com.projectschedule.ruby.entity.enumItem.ScheduleKind;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;

/**
 *  스케쥴 Entity
 *      - 스케쥴 명
 *      - 스케쥴 시작일
 *      - 스케쥴 종료일
 *      - 진행상태 (진행 / 완료)
 *
 *  Schedule : User = N : 1
 *  Schedule : ScheduleItem = 1 : N
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Schedule {

    public Schedule(Long id, String scheduleName, LocalDate startDay, LocalDate endDay, ProgressStatus status) {
        this.id = id;
        this.scheduleName = scheduleName;
        this.startDay = startDay;
        this.endDay = endDay;
        this.status = status;
    }

    @Id
    @GeneratedValue
    @Column(name = "schedule_id")
    private Long id;
    @NotNull @NotEmpty
    private String scheduleName;            // 스케쥴 명
    @NotNull @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDay;             // 시작일
    @NotNull @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDay;               // 종료일
    @NotNull @Enumerated(STRING)
    private ScheduleKind kind;              // 스케쥴 종류
    @NotNull @Enumerated(STRING)
    private ProgressStatus status;          // 진행상태

    /**
     * Schedule 과 ScheduleItem 의 연관관계의 주인은  ScheduleItem 이다.
     *  - 연관관계의 주인이 아니면 mappedBy 속성을 사용하고 연관관계의 주인 필드 이름의 값으로 입력해야한다.
     */
    @BatchSize(size = 6)
    @OneToMany(mappedBy = "schedule")
    private List<ScheduleItem> scheduleItemList = new ArrayList<>();

    /**
     * 연관관게의 주인인 필드는 외래 키를 name 속성에 기재한다.
     */
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Schedule(Builder builder) {
        this.scheduleName = builder.scheduleName;
        this.startDay = builder.startDay;
        this.endDay = builder.endDay;
        this.kind = builder.kind;
        this.status = builder.status;
        this.member = builder.member;
    }

    /**
     * 빌더
     */
    public static class Builder {

        private String scheduleName;
        private LocalDate startDay;
        private LocalDate endDay;
        private ScheduleKind kind;
        private ProgressStatus status;
        private Member member;

        public Builder(){}

        public Builder scheduleName(String scheduleName) {
            this.scheduleName = scheduleName;
            return this;
        }

        public Builder startDay(LocalDate startDay) {
            this.startDay = startDay;
            return this;
        }

        public Builder endDay(LocalDate endDay) {
            this.endDay = endDay;
            return this;
        }

        public Builder kind(ScheduleKind kind) {
            this.kind = kind;
            return this;
        }

        public Builder status(ProgressStatus status) {
            this.status = status;
            return this;
        }

        public Builder member(Member member) {
            this.member = member;
            return this;
        }

        public Schedule build() {
            return new Schedule(this);
        }
    }

    // 연관관계 편의 메서드 - 양쪽애 모두 만들 수 있지만 다(N) 쪽에 하나만 만들어도 충분하다
    public void setMember(Member member){
        this.member = member;

        if (!member.getScheduleList().contains(this)){
            member.getScheduleList().add(this);
        }
    }

    public void addScheduleItemList(ScheduleItem scheduleItem) {
        scheduleItemList.add(scheduleItem);

        if (scheduleItem.getSchedule() != this) {
            scheduleItem.setSchedule(this);
        }
    }


    /** 비즈니스 메서드 */

    /**
     * 스케쥴 이름 변경
     * @param scheduleName
     * @return
     */
    public Schedule modifyScheduleName(String scheduleName) {
        this.scheduleName = scheduleName;
        return this;
    }

    /**
     * 스케쥴 시작날짜 변경
     * @param startDay
     * @return
     */
    public Schedule modifyStartDay(LocalDate startDay) {
        this.startDay = startDay;
        return this;
    }

    /**
     * 스케쥴 종료날짜 변경
     * @param endDay
     * @return
     */
    public Schedule modifyEndDay(LocalDate endDay) {
        this.endDay = endDay;
        return this;
    }

    /**
     * 스케쥴 상태 변경
     * 스케쥴 시작날짜 변경
     * @param status
     * @return
     */
    public Schedule modifyStatus(ProgressStatus status) {
        this.status = status;
        return this;
    }
}

