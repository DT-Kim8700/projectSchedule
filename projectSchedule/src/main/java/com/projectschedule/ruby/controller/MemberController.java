package com.projectschedule.ruby.controller;

import com.projectschedule.ruby.entity.Member;
import com.projectschedule.ruby.entity.Schedule;
import com.projectschedule.ruby.entity.dto.MemberDto;
import com.projectschedule.ruby.entity.dto.ScheduleListDto;
import com.projectschedule.ruby.service.member.MemberService;
import com.projectschedule.ruby.service.schedule.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final ScheduleService scheduleService;

    /**
     * 로그인 처리
     * @param memberDto
     * @return
     */
    @RequestMapping("/login")
    public ScheduleListDto login(@Valid @RequestBody MemberDto memberDto) {
        System.out.println("email: " + memberDto.getEmail());
        System.out.println("password: " + memberDto.getPassword());

        // 회원 정보 검증
        String email = memberDto.getEmail();
        String password = memberDto.getPassword();
        Member member = memberService.loginMember(email, password);

        // 검증 성공시 해당 회원의 스케쥴 정보 조회
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");
        if (member != null) {
            Pageable pageable = PageRequest.of(2, 6);
            Page<Schedule> schedules = scheduleService.lookupScheduleList(member, pageable);
//            Page<Schedule> schedules = scheduleService.lookupScheduleListV2(member, pageable);
            ScheduleListDto scheduleListDto = new ScheduleListDto(schedules);
            return scheduleListDto;
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");

        return null;
    }
}
