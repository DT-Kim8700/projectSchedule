package com.projectschedule.ruby.service.member;

import com.projectschedule.ruby.entity.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceImplTest {

    @Autowired
    EntityManager em;
    JPAQueryFactory queryFactory;

    @Autowired
    MemberService memberService;

    /**
     * 테스트케이스 실행전에 먼저 실행된다. 테스트케이스에 필요한 데이터를 미리 셋팅
     */
    @BeforeEach
    public void before() {
        Member member = new Member.Builder()
                .email("ruby8700@naver.com")
                .name("ruby")
                .password("12345678")
                .build();

        em.persist(member);

        queryFactory = new JPAQueryFactory(em);

        em.flush();
        em.clear();
    }

    /**
     * 중복회원 검증
     */
    @Test
    public void validateDuplicateMember() {
        Member member = new Member.Builder()
                .email("ruby8700@naver.com")
                .name("ruby")
                .password("12345678")
                .build();

        assertThrows(IllegalStateException.class, () -> {memberService.addMember(member);});
    }
}