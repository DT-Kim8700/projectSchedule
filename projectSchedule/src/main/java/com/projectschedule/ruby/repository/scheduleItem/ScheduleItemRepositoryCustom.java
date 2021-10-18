package com.projectschedule.ruby.repository.scheduleItem;

import com.projectschedule.ruby.entity.Schedule;
import com.projectschedule.ruby.entity.ScheduleItem;

import java.util.List;

public interface ScheduleItemRepositoryCustom {

    /**
     * Schedule 에 해당하는 ScheduleItem 목록 삭제
     * @param scheduleId
     */
    void deleteScheduleItemBySchedule(Long scheduleId);

    /**
     * 유저의 스케쥴 목록 조회
     * @param scheduleId
     * @return
     */
    List<ScheduleItem> findScheduleItemsBySchedule(Long scheduleId);
}
