package com.projectschedule.ruby.repository.scheduleItem;

import com.projectschedule.ruby.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *  스케쥴 Item Repository
 */
@Repository
public interface ScheduleItemRepository extends JpaRepository<Schedule, Long>, ScheduleItemRepositoryCustom {

}
