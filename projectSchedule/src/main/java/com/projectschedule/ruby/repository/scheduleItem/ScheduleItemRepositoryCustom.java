package com.projectschedule.ruby.repository.scheduleItem;

public interface ScheduleItemRepositoryCustom {

    /**
     * Schedule 에 해당하는 ScheduleItem 목록 삭제
     * @param scheduleId
     */
    void deleteScheduleItemBySchedule(Long scheduleId);
}
