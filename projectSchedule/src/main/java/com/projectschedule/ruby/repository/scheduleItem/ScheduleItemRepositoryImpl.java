package com.projectschedule.ruby.repository.scheduleItem;

import com.projectschedule.ruby.entity.Schedule;
import com.projectschedule.ruby.entity.ScheduleItem;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.projectschedule.ruby.entity.QSchedule.schedule;
import static com.projectschedule.ruby.entity.QScheduleItem.scheduleItem;

@RequiredArgsConstructor
public class ScheduleItemRepositoryImpl implements ScheduleItemRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    /**
     * Schedule 에 해당하는 ScheduleItem 목록 모두 삭제
     *
     * @param schedule
     */
    @Override
    public void deleteScheduleItemBySchedule(Schedule schedule) {
        queryFactory
                .delete(scheduleItem)
                .where(scheduleItem.schedule.eq(schedule))
                .execute();
    }
}
