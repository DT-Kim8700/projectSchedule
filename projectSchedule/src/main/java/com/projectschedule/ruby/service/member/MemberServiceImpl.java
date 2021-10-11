package com.projectschedule.ruby.service.member;

import com.projectschedule.ruby.entity.Member;
import com.projectschedule.ruby.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    /**
     * 회원 등록 TODO : Securty 적용
     *
     * @param member
     * @return
     */
    @Override
    public Long addMember(Member member) {

        // 해당 email 로 가입된 정보가 있는지 확인한다.
        // 없을 때 가입을 진행한다.
        validateDuplicateMember(member);
        memberRepository.save(member);

        return member.getId();
    }

    /**
     * 회원 로그인 TODO : Securty 적용
     *
     * @param member
     * @return
     */
    @Override
    public Member loginMember(Member member) {


        return null;
    }


    /**
     * email 같은 회원 중복 검증
     * @param member
     */
    private void validateDuplicateMember(Member member) {
        String email = member.getEmail();
        List<Member> findMembers = memberRepository.findByEmail(email);

        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
}
