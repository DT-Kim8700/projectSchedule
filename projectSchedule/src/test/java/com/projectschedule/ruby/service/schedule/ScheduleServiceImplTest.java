package com.projectschedule.ruby.service.schedule;

import com.projectschedule.ruby.entity.Member;
import com.projectschedule.ruby.entity.Schedule;
import com.projectschedule.ruby.entity.ScheduleItem;
import com.projectschedule.ruby.entity.enumItem.ProgressStatus;
import com.projectschedule.ruby.service.TestSeed;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

class ScheduleServiceImplTest extends TestSeed {

    /**
     * 스케쥴 목록 조회1
     */
    @Test
    void lookupScheduleList() {
        String email = "ruby8700@naver.com";
        String password = "12345678";

        Member member = new Member.Builder()
                .email(email)
                .password(password)
                .build();

        Member loginMember = memberService.loginMember(member);

        PageRequest pageRequest = PageRequest.of(1, 6);
        Page<Schedule> schedules = scheduleService.lookupScheduleList(loginMember, pageRequest);

        assertThat(schedules.getSize()).isEqualTo(6);
        assertThat(schedules.getContent()).extracting("scheduleName")
                .containsExactly("스케쥴24", "스케쥴23", "스케쥴22","스케쥴21", "스케쥴20", "스케쥴19");
        assertThat(schedules.getContent().get(0).getMember().getEmail())
                .isEqualTo(email);
    }

    /**
     * 스케쥴 목록 조회2
     */
    @Test
    void lookupScheduleList2() {
        String email = "eun@naver.com";
        String password = "12345678";

        Member member = new Member.Builder()
                .email(email)
                .password(password)
                .build();

        Member loginMember = memberService.loginMember(member);

        PageRequest pageRequest = PageRequest.of(1, 6);
        Page<Schedule> schedules = scheduleService.lookupScheduleList(loginMember, pageRequest);

        assertThat(schedules.getTotalElements()).isEqualTo(0);
        assertThat(schedules.getTotalPages()).isEqualTo(0);
    }

    /**
     * 스케쥴 목록 조회3
     */
    @Test
    void lookupScheduleList3() {
        String email = "ruby8700@naver.com";
        String password = "12345678";

        Member member = new Member.Builder()
                .email(email)
                .password(password)
                .build();

        Member loginMember = memberService.loginMember(member);

        System.out.println("===============lookupScheduleList3====================");

        PageRequest pageRequest = PageRequest.of(0, 6);
        Page<Schedule> schedules = scheduleService.lookupScheduleList(loginMember, pageRequest);

        System.out.println("size: " + schedules.getContent().size());

        for (Schedule schedule :schedules) {
            System.out.println(schedule.getScheduleName());
            for (ScheduleItem item :schedule.getScheduleItemList()) {
                System.out.println(item.getItemName());
            }
        }

        System.out.println("===============lookupScheduleList3====================");
    }


    /**
     * 스케쥴 등록
     */
    @Test
    public void addSchedule() {
        // given
        String email = "ruby8700@naver.com";
        String password = "12345678";

        Member member = new Member.Builder()
                .email(email)
                .password(password)
                .build();

        Member loginMember = memberService.loginMember(member);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate startDay = LocalDate.parse("2021-10-11", formatter);
        LocalDate endDay = LocalDate.parse("2021-10-21", formatter);

        Schedule schedule = new Schedule.Builder()
                .scheduleName("스케쥴")
                .startDay(startDay)
                .endDay(endDay)
                .status(ProgressStatus.PROCEED)
                .member(loginMember)
                .build();

        // when
        scheduleService.addSchedule(schedule);

        // then
        PageRequest pageRequest = PageRequest.of(0, 1);
        Page<Schedule> schedules = scheduleService.lookupScheduleList(loginMember, pageRequest);
        assertThat(schedules.getContent()).extracting("scheduleName")
                .containsExactly("스케쥴");
    }

    /**
     * 스케쥴 갱신
     */
    @Test
    public void modifySchedule() {
        // given
        String email = "ruby8700@naver.com";
        String password = "12345678";

        Member member = new Member.Builder()
                .email(email)
                .password(password)
                .build();

        Member loginMember = memberService.loginMember(member);

        PageRequest pageRequest = PageRequest.of(0, 1);
        Schedule schedule = scheduleService.lookupScheduleList(loginMember, pageRequest).getContent().get(0);

        // when
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDay = LocalDate.parse("2021-11-21", formatter);
        LocalDate endDay = LocalDate.parse("2021-11-29", formatter);

        schedule.modifyScheduleName("스케쥴변경")
                .modifyStartDay(startDay)
                .modifyEndDay(endDay)
                .modifyStatus(ProgressStatus.COMPLETE);

        Schedule findSchedule = scheduleService.lookupScheduleList(loginMember, pageRequest).getContent().get(0);

        // then
        assertThat(findSchedule.getId()).isEqualTo(schedule.getId());
        assertThat(findSchedule.getScheduleName()).isEqualTo("스케쥴변경");
        assertThat(findSchedule.getStartDay()).isEqualTo(startDay);
        assertThat(findSchedule.getEndDay()).isEqualTo(endDay);
        assertThat(findSchedule.getStatus()).isEqualTo(ProgressStatus.COMPLETE);
    }


}