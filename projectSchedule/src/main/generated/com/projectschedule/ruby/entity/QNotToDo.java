package com.projectschedule.ruby.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QNotToDo is a Querydsl query type for NotToDo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QNotToDo extends EntityPathBase<NotToDo> {

    private static final long serialVersionUID = 289694999L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QNotToDo notToDo = new QNotToDo("notToDo");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    public final StringPath notToDoName = createString("notToDoName");

    public final StringPath remark = createString("remark");

    public QNotToDo(String variable) {
        this(NotToDo.class, forVariable(variable), INITS);
    }

    public QNotToDo(Path<? extends NotToDo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QNotToDo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QNotToDo(PathMetadata metadata, PathInits inits) {
        this(NotToDo.class, metadata, inits);
    }

    public QNotToDo(Class<? extends NotToDo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

