package com.projectschedule.ruby.repository.schedule;

import com.projectschedule.ruby.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *  스케쥴 Repository
 *      - 스케쥴 조회
 *      - 스케쥴 등록    - save
 *      - 스케쥴 수정    - save
 *      - 스케쥴 삭제    - delete
 */
public interface ScheduleRepository extends JpaRepository<Schedule, Long>, ScheduleRepositoryCustom {

}
