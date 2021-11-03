package com.projectschedule.ruby.entity.dto;


import com.projectschedule.ruby.entity.Schedule;
import com.projectschedule.ruby.entity.ScheduleItem;
import lombok.Getter;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

/**
 * fetch join 으로 조회한 Schedule, ScheduleItem 엔티티들을 Dto로 변환한 클래스
 *      - scheduleDtos  : Schedule 목록, Schedule에 속한 ScheduleItem 목록
 *      - pageable      : 페이징 정보가 담긴 객체
 */
@Getter
public class ScheduleListDto {

    private List<ScheduleDto> scheduleList;
    private Pageable pageable;

    public ScheduleListDto(Page<Schedule> schedules) {
        this.pageable = schedules.getPageable();
        convertScheduleDtos(schedules.getContent());
    }

    /**
     * fetch join 으로 조회한 Schedule, ScheduleItem 엔티티들을 Dto로 변환
     * @param scheduleList
     */
    private void convertScheduleDtos(List<Schedule> scheduleList) {
        this.scheduleList = new ArrayList<>();
        for (Schedule schedule : scheduleList) {
            // schedule 엔티티에서 scheduleDto 로 변환
            ScheduleDto scheduleDto = new ScheduleDto();
            BeanUtils.copyProperties(schedule, scheduleDto);

            // ScheduleItem 엔티티에서 ScheduleItemDto 로 변환
            List<ScheduleItem> scheduleItemList = schedule.getScheduleItemList();
            List<ScheduleItemDto> scheduleItemDtos = new ArrayList<>();
            for (ScheduleItem item : scheduleItemList) {
                ScheduleItemDto scheduleItemDto = new ScheduleItemDto();
                BeanUtils.copyProperties(item, scheduleItemDto);
                scheduleItemDtos.add(scheduleItemDto);
            }
            scheduleDto.setScheduleItemList(scheduleItemDtos);
            this.scheduleList.add(scheduleDto);
        }
    }
}
