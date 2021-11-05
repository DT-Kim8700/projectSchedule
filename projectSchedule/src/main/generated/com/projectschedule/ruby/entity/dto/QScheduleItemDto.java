package com.projectschedule.ruby.entity.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.Generated;

/**
 * com.projectschedule.ruby.entity.dto.QScheduleItemDto is a Querydsl Projection type for ScheduleItemDto
 */
@Generated("com.querydsl.codegen.ProjectionSerializer")
public class QScheduleItemDto extends ConstructorExpression<ScheduleItemDto> {

    private static final long serialVersionUID = 561241956L;

    public QScheduleItemDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> itemName, com.querydsl.core.types.Expression<Integer> progress) {
        super(ScheduleItemDto.class, new Class<?>[]{long.class, String.class, int.class}, id, itemName, progress);
    }

}

