package com.projectschedule.ruby.repository.scheduleItem;

import com.projectschedule.ruby.entity.Schedule;
import com.projectschedule.ruby.entity.ScheduleItem;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static com.projectschedule.ruby.entity.QSchedule.schedule;
import static com.projectschedule.ruby.entity.QScheduleItem.scheduleItem;

@RequiredArgsConstructor
public class ScheduleItemRepositoryImpl implements ScheduleItemRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    /**
     * Schedule 에 해당하는 ScheduleItem 목록 삭제
     *
     * @param scheduleId
     */
    @Override
    public void deleteScheduleItemBySchedule(Long scheduleId) {
        queryFactory
                .delete(scheduleItem)
                .where(scheduleItem.schedule.id.eq(scheduleId));
    }

    /**
     * 유저의 스케쥴 목록 조회
     *
     * @param scheduleId
     * @return
     */
    @Override
    public List<ScheduleItem> findScheduleItemsBySchedule(Long scheduleId) {
        return queryFactory
                .selectFrom(scheduleItem)
                .where(scheduleItem.schedule.id.eq(scheduleId))
                .fetch();
    }
}
