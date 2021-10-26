package com.projectschedule.ruby.repository.scheduleItem;

import com.projectschedule.ruby.entity.Schedule;

public interface ScheduleItemRepositoryCustom {

    /**
     * Schedule 에 해당하는 ScheduleItem 목록 삭제
     * @param schedule
     */
    void deleteScheduleItemBySchedule(Schedule schedule);
}
