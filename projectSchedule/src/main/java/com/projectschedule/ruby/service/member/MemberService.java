package com.projectschedule.ruby.service.member;

import com.projectschedule.ruby.entity.Member;
import com.projectschedule.ruby.entity.dto.MemberDto;

/**
 * Member 서비스
 *  - 회원 등록
 *  - 회원 조회
 */
public interface MemberService {

    /**
     * 회원 등록
     * @param member
     * @return
     */
    Long addMember(Member member);

    /**
     * 회원 로그인
     * @param member
     * @return
     */
    Member loginMember(Member member);
}
