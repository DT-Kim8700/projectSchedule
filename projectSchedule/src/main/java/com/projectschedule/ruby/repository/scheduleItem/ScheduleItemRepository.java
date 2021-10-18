package com.projectschedule.ruby.repository.scheduleItem;

import com.projectschedule.ruby.entity.ScheduleItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *  스케쥴 Item Repository
 */
public interface ScheduleItemRepository extends JpaRepository<ScheduleItem, Long>, ScheduleItemRepositoryCustom {

}
