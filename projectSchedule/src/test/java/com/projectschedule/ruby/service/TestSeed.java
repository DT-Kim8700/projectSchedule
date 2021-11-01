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
    protected ScheduleItemService ScheduleItemService;

    @BeforeEach
    public void before() {
        Member member = new Member.Builder()
                .email("ruby8700@naver.com")
                .name("ruby")
                .password("12345678")
                .build();

        em.persist(member);

        for (int i = 1; i <= 30; i++) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            LocalDate startDay = LocalDate.parse("2021-10-11", formatter);
            LocalDate endDay = LocalDate.parse("2021-10-21", formatter);

            Schedule schedule = new Schedule.Builder()
                    .scheduleName("스케쥴" + i)
                    .startDay(startDay)
                    .endDay(endDay)
                    .kind(ScheduleKind.STUDY)
                    .status(ProgressStatus.PROCEED)
                    .member(member)
                    .build();

            em.persist(schedule);
        }

        PageRequest pageRequest = PageRequest.of(0, 6);
        Page<Schedule> schedules = scheduleService.lookupScheduleList(member, pageRequest);
        Schedule schedule = schedules.getContent().get(0);

        for (int i = 1; i <= 4; i++) {
            ScheduleItem scheduleItem = new ScheduleItem.Builder()
                    .itemName("알고리즘 공부" + i)
                    .progress(15 * i)
                    .schedule(schedule)
                    .build();

            em.persist(scheduleItem);
        }

        schedule = schedules.getContent().get(1);

        for (int i = 1; i <= 4; i++) {
            ScheduleItem scheduleItem = new ScheduleItem.Builder()
                    .itemName("JPA 공부" + i)
                    .progress(15 * i)
                    .schedule(schedule)
                    .build();

            em.persist(scheduleItem);
        }
        em.flush();
        em.clear();

        queryFactory = new JPAQueryFactory(em);
    }
}
