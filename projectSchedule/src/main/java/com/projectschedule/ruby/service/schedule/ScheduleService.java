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
 *  - 스케줄 종류별 개수 반환
 */
public interface ScheduleService {

    /**
     * 스케쥴 목록 조회
     * @param member
     * @param pageable
     * @return
     */
    Page<Schedule> lookupScheduleList(Member member, Pageable pageable);

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
     * @param schedule
     * @return
     */
    void removeSchedule(Schedule schedule);

    /**
     * 스케줄 종류별 개수 반환
     */
    void lookupScheduleKindCountAll(Member member);
}
