package com.projectschedule.ruby.entity;

import com.projectschedule.ruby.entity.enumItem.ProgressStatus;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EnumPath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
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

    public Schedule(Long id, String scheduleName, LocalDateTime startDay, LocalDateTime endDay, ProgressStatus status) {
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
    @NotEmpty
    private String scheduleName;            // 스케쥴 명
    @NotEmpty @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime startDay;         // 시작일
    @NotEmpty @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime endDay;           // 종료일
    @NotEmpty @Enumerated(STRING)
    private ProgressStatus status;          // 진행상태

    /**
     * Schedule 과 ScheduleItem 의 연관관계의 주인은  ScheduleItem 이다.
     *  - 연관관계의 주인이 아니면 mappedBy 속성을 사용하고 연관관계의 주인 필드 이름의 값으로 입력해야한다.
     */
    @OneToMany(mappedBy = "schedule" , cascade = CascadeType.ALL)
    private List<ScheduleItem> scheduleItemList = new ArrayList<>();

    /**
     * 연관관게의 주인인 필드는 외래 키를 name 속성에 기재한다.
     */
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    
    // 연관관계 편의 메서드 - 양쪽애 모두 만들 수 있지만 다(N) 쪽에 하나만 만들어도 충분하다
    public void setMember(Member member){
        this.member = member;

        if (!member.getScheduleList().contains(this)){
            member.getScheduleList().add(this);
        }
    }


    /** 비즈니스 메서드 */

    /**
     * 스케쥴 갱신
     * @param schedule
     * @return
     */
    public Schedule modifySchedule(Schedule schedule) {
        this.scheduleName = schedule.getScheduleName();
        this.startDay = schedule.getStartDay();
        this.endDay = schedule.getEndDay();
        this.status = schedule.getStatus();

        return this;
    }
}
