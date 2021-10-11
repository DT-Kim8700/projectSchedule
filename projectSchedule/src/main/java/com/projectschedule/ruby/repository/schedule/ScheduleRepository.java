package com.projectschedule.ruby.repository.schedule;

import com.projectschedule.ruby.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *  스케쥴 Repository
 *      - 스케쥴 조회
 *      - 스케쥴 등록
 *      - 스케쥴 수정
 *      - 스케쥴 삭제
 */
public interface ScheduleRepository extends JpaRepository<Schedule, Long>, ScheduleRepositoryCustom {

}
