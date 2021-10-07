package com.projectschedule.ruby.repository.schedule;

import com.projectschedule.ruby.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ScheduleRepositoryCustom {

    /**
     * 유저의 스케쥴 목록 조회
     * @param memberId
     * @param pageable
     * @return
     */
    Page<Schedule> selectScheduleByMember(Long memberId, Pageable pageable);
}
