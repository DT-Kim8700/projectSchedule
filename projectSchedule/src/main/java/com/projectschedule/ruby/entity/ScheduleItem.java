package com.projectschedule.ruby.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import static javax.persistence.FetchType.LAZY;

/**
 *  스케쥴 세부항목 Entity
 *      - 세부항목 명
 *      - 세부항목 시작일
 *      - 세부항목 종료일
 *      - 진행률
 *
 *  ScheduleItem : Schedule = N : 1
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ScheduleItem {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;
    @NotEmpty
    private String itemName;                // 세부항목 명
    @NotNull @Min(0) @Max(100)
    private Integer progress;                  // 진행률

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    public ScheduleItem(Builder builder) {
        this.itemName = builder.itemName;
        this.progress = builder.progress;
        this.schedule = builder.schedule;
    }

    /**
     * 빌더
     */
    public static class Builder {

        private String itemName;
        private Integer progress;
        private Schedule schedule;

        public Builder(){}

        public Builder itemName(String itemName) {
            this.itemName = itemName;
            return this;
        }

        public Builder progress(Integer progress) {
            this.progress = progress;
            return this;
        }

        public Builder schedule(Schedule schedule) {
            this.schedule = schedule;
            return this;
        }

        public ScheduleItem build() {
            return new ScheduleItem(this);
        }
    }

    // 연관관계 편의 메서드
    public void setSchedule(Schedule schedule){
        this.schedule = schedule;

        if (!schedule.getScheduleItemList().contains(this)){
            schedule.getScheduleItemList().add(this);
        }
    }
}
