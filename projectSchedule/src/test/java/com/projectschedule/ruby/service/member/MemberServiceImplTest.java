package com.projectschedule.ruby.service.member;

import com.projectschedule.ruby.entity.Member;
import com.projectschedule.ruby.service.TestSeed;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceImplTest extends TestSeed {

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