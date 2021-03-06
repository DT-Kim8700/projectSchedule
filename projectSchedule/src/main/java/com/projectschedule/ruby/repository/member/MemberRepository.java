package com.projectschedule.ruby.repository.member;

import com.projectschedule.ruby.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {

    /**
     * Email로 해당 회원이 있는지 검색
     * @param email
     * @return
     */
    List<Member> findByEmail(String email);

    /**
     * Email 과 Password로 해당 회원 인증
     * @param email
     * @param password
     * @return
     */
    Member findByEmailAndPassword(String email, String password);
}
