package com.projectschedule.ruby.repository.schedule;

import com.projectschedule.ruby.entity.Member;
import com.projectschedule.ruby.entity.Schedule;
import com.projectschedule.ruby.entity.dto.ScheduleKindDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 *  스케쥴 Repository
 *      - 스케쥴 조회
 *      - 스케쥴 등록
 *      - 스케쥴 수정
 *      - 스케쥴 삭제
 */
public interface ScheduleRepository extends JpaRepository<Schedule, Long>, ScheduleRepositoryCustom {

    /**
     * 유저의 스케쥴 목록 조회
     * @param member
     * @param pageable
     * @return
     */
    Page<Schedule> findAllByMember(Member member, Pageable pageable);

    /**
     * 스케쥴 종류별 개수 조회
     * @param member
     * @return
     */
    List<ScheduleKindDto> selectKindCountAll(Member member);
}
