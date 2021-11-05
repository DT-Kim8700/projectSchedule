package com.projectschedule.ruby.service;

import com.projectschedule.ruby.entity.Member;
import com.projectschedule.ruby.entity.Schedule;
import com.projectschedule.ruby.entity.ScheduleItem;
import com.projectschedule.ruby.entity.enumItem.ProgressStatus;
import com.projectschedule.ruby.entity.enumItem.ScheduleKind;
import com.projectschedule.ruby.repository.member.MemberRepository;
import com.projectschedule.ruby.repository.schedule.ScheduleRepository;
import com.projectschedule.ruby.repository.scheduleItem.ScheduleItemRepository;
import com.projectschedule.ruby.service.member.MemberService;
import com.projectschedule.ruby.service.schedule.ScheduleService;
import com.projectschedule.ruby.service.scheduleItem.ScheduleItemService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@SpringBootTest
@Transactional
public class TestSeed {

    @Autowired
    protected EntityManager em;
    protected JPAQueryFactory queryFactory;

    @Autowired
    protected MemberRepository memberRepository;
    @Autowired
    protected ScheduleRepository scheduleRepository;
    @Autowired
    protected ScheduleItemRepository scheduleItemRepository;

    @Autowired
    protected MemberService memberService;
    @Autowired
    protected ScheduleService scheduleService;
    @Autowired
    protected ScheduleItemService scheduleItemService;

    @BeforeEach
    public void before() {
        Member member = new Member.Builder()
                .email("ruby8700@naver.com")
                .name("ruby")
                .password("12345678")
                .build();

        memberRepository.save(member);

        for (int i = 1; i <= 30; i++) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            LocalDate startDay = LocalDate.parse("2021-10-11", formatter);
            LocalDate endDay = LocalDate.parse("2021-10-21", formatter);

            String prefix = null;
            ScheduleKind kind = ScheduleKind.STUDY;
            if (i % 4 == 0) {
                prefix = "알고리즘";
                kind = ScheduleKind.STUDY;
            } else if (i % 4 == 1) {
                prefix = "악기연습";
                kind = ScheduleKind.HOBBY;
            } else if (i % 4 == 2) {
                prefix = "맛집탐방";
                kind = ScheduleKind.TRAVEL;
            } else if (i % 4 == 3) {
                prefix = "휴식";
                kind = ScheduleKind.ECT;
            }

            Schedule schedule = new Schedule.Builder()
                    .scheduleName(prefix + i)
                    .startDay(startDay)
                    .endDay(endDay)
                    .kind(kind)
                    .status(ProgressStatus.PROCEED)
                    .member(member)
                    .build();

            scheduleService.addSchedule(schedule);

            for (int j = 0; j < 4; j++) {
                ScheduleItem scheduleItem = new ScheduleItem.Builder()
                        .itemName(prefix + j)
                        .progress(15 * j)
                        .schedule(schedule)
                        .build();

                scheduleItemService.addScheduleItem(scheduleItem);
            }
        }

        queryFactory = new JPAQueryFactory(em);
    }
}
