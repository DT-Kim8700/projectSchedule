package com.projectschedule.ruby.entity.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.Generated;

/**
 * com.projectschedule.ruby.entity.dto.QScheduleKindDto is a Querydsl Projection type for ScheduleKindDto
 */
@Generated("com.querydsl.codegen.ProjectionSerializer")
public class QScheduleKindDto extends ConstructorExpression<ScheduleKindDto> {

    private static final long serialVersionUID = 2029372227L;

    public QScheduleKindDto(com.querydsl.core.types.Expression<com.projectschedule.ruby.entity.enumItem.ScheduleKind> kind, com.querydsl.core.types.Expression<Long> kindCount) {
        super(ScheduleKindDto.class, new Class<?>[]{com.projectschedule.ruby.entity.enumItem.ScheduleKind.class, long.class}, kind, kindCount);
    }

}

