package com.projectschedule.ruby.repository.member;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberRepositoryImpl {

    private final JPAQueryFactory queryFactory;
}
