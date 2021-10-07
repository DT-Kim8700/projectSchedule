package com.projectschedule.ruby.repository.scheduleItem;

import com.projectschedule.ruby.entity.QScheduleItem;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

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
}
