package com.projectschedule.ruby.repository.schedule;

import com.projectschedule.ruby.entity.Member;
import com.projectschedule.ruby.entity.QSchedule;
import com.projectschedule.ruby.entity.dto.QScheduleKindDto;
import com.projectschedule.ruby.entity.dto.ScheduleKindDto;
import com.projectschedule.ruby.entity.enumItem.ScheduleKind;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.projectschedule.ruby.entity.QSchedule.*;

@RequiredArgsConstructor
public class ScheduleRepositoryImpl implements ScheduleRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    /**
     * 스케쥴 종류별 개수 조회
     * @param member
     * @return
     */
    public List<ScheduleKindDto> selectKindCountAll(Member member) {
        return queryFactory
                .select(new QScheduleKindDto(schedule.kind, schedule.kind.count()))
                .from(schedule)
                .where(schedule.member.eq(member))
                .groupBy(schedule.kind)
                .fetch();
    }
}
