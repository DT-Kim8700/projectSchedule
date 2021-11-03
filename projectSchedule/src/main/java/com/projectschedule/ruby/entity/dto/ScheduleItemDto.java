package com.projectschedule.ruby.entity.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ScheduleItemDto {

    @QueryProjection
    public ScheduleItemDto(Long id, String itemName, Integer progress) {
        this.id = id;
        this.itemName = itemName;
        this.progress = progress;
    }

    private Long id;
    private String itemName;                        // 세부항목 명
    private Integer progress;                       // 진행률
}
