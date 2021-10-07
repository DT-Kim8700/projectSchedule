package com.projectschedule.ruby.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

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
public class ScheduleItem {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;
    @NotEmpty
    private String itemName;                // 세부항목 명
    @NotEmpty @Min(0) @Max(100)
    private Long progress;                  // 진행률

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    // 연관관계 편의 메서드
    public void setSchedule(Schedule schedule){
        this.schedule = schedule;

        if (!schedule.getScheduleItemList().contains(this)){
            schedule.getScheduleItemList().add(this);
        }
    }
}
