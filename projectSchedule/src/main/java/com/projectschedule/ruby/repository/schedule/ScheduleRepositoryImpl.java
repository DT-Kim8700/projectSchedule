package com.projectschedule.ruby.repository.schedule;

import com.projectschedule.ruby.entity.Member;
import com.projectschedule.ruby.entity.Schedule;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.projectschedule.ruby.entity.QSchedule.schedule;

@RequiredArgsConstructor
public class ScheduleRepositoryImpl implements ScheduleRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    /**
     * 유저의 스케쥴 목록 조회 V2
     *  -
     * @param member
     * @param pageable
     * @return
     */
    @Override
    public Page<Schedule> selectScheduleByMember(Member member, Pageable pageable) {
        JPAQuery<Schedule> countQuery = queryFactory
                .select(schedule)
                .from(schedule)
                .where(schedule.member.eq(member));

        List<Schedule> scheduleList =  queryFactory
                .selectFrom(schedule)
                .where(schedule.member.eq(member))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return PageableExecutionUtils.getPage(scheduleList, pageable, countQuery::fetchCount);
    }
}
