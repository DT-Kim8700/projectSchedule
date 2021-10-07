package com.projectschedule.ruby.service.schedule;

import com.projectschedule.ruby.entity.Member;
import com.projectschedule.ruby.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 스케쥴 Service
 *  - 스케쥴 목록 조회
 *  - 스케쥴 등록
 *  - 스케쥴 갱신
 *  - 스케쥴 삭제
 */
public interface ScheduleService {

    /**
     * 스케쥴 목록 조회
     * @param memberId
     * @param pageable
     * @return
     */
    Page<Schedule> lookupScheduleList(Long memberId, Pageable pageable);

    /**
     * 스케쥴 등록
     * @param schedule
     * @return
     */
    void addSchedule(Schedule schedule);

    /**
     * 스케쥴 갱신
     * @param schedule
     * @return
     */
    void modifySchedule(Schedule schedule);

    /**
     * 스케쥴 삭제
     * @param scheduleId
     * @return
     */
    void removeSchedule(Long scheduleId);
}
