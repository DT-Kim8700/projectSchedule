package com.projectschedule.ruby.repository.scheduleItem;

import com.projectschedule.ruby.entity.ScheduleItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *  스케쥴 Item Repository
 *      - 스케쥴 Item 조회       - 스케쥴 조회시 해당 item도 같이 조회로 처리
 *      - 스케쥴 등록            - save
 *      - 스케쥴 수정            - save
 *      - 스케쥴 삭제            - delete
 */
public interface ScheduleItemRepository extends JpaRepository<ScheduleItem, Long>, ScheduleItemRepositoryCustom {

}
