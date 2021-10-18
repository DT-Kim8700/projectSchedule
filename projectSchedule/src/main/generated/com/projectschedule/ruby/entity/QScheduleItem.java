package com.projectschedule.ruby.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QScheduleItem is a Querydsl query type for ScheduleItem
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QScheduleItem extends EntityPathBase<ScheduleItem> {

    private static final long serialVersionUID = -725339700L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QScheduleItem scheduleItem = new QScheduleItem("scheduleItem");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath itemName = createString("itemName");

    public final NumberPath<Integer> progress = createNumber("progress", Integer.class);

    public final QSchedule schedule;

    public QScheduleItem(String variable) {
        this(ScheduleItem.class, forVariable(variable), INITS);
    }

    public QScheduleItem(Path<? extends ScheduleItem> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QScheduleItem(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QScheduleItem(PathMetadata metadata, PathInits inits) {
        this(ScheduleItem.class, metadata, inits);
    }

    public QScheduleItem(Class<? extends ScheduleItem> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.schedule = inits.isInitialized("schedule") ? new QSchedule(forProperty("schedule"), inits.get("schedule")) : null;
    }

}

