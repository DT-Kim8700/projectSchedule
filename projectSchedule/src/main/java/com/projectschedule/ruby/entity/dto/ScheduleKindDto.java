package com.projectschedule.ruby.entity.dto;

import com.projectschedule.ruby.entity.enumItem.ScheduleKind;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 스케쥴 종류와 해당 종류의 개수 Dto
 */
@Getter
@Setter
@NoArgsConstructor
public class ScheduleKindDto {

    @QueryProjection
    public ScheduleKindDto(ScheduleKind kind, Long kindCount) {
        this.kind = kind;
        this.kindCount = kindCount;
    }

    private ScheduleKind kind;          // 스케쥴 종류
    private Long kindCount;             // 종류 개수
}
