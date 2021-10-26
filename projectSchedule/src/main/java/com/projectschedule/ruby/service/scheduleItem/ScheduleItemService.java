package com.projectschedule.ruby.service.scheduleItem;

import com.projectschedule.ruby.entity.ScheduleItem;

/**
 * 스케쥴 세부목록 Service
 *  - 스케쥴에 속한 세부목록 조회(스케쥴 조회시 같이 조회)
 *  - 스케쥴 세부목록 등록
 *  - 스케쥴 세부목록 갱신
 *  - 스케쥴 세부목록 삭제
 */
public interface ScheduleItemService {

    /**
     * 스케쥴 세부목록 등록
     *
     * @param scheduleItem
     * @return
     */
    void addScheduleItem(ScheduleItem scheduleItem);

    /**
     * 스케쥴 세부목록 갱신
     * @param scheduleItem
     * @return
     */
    void modifyScheduleItem(ScheduleItem scheduleItem);

    /**
     * 스케쥴 세부목록 삭제
     * @param scheduleItem
     * @return
     */
    void removeScheduleItem(ScheduleItem scheduleItem);
}