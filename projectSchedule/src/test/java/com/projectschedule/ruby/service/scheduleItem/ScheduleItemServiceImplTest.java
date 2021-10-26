package com.projectschedule.ruby.service.scheduleItem;

import com.projectschedule.ruby.entity.Member;
import com.projectschedule.ruby.entity.Schedule;
import com.projectschedule.ruby.entity.ScheduleItem;
import com.projectschedule.ruby.entity.dto.MemberDto;
import com.projectschedule.ruby.entity.enumItem.ProgressStatus;
import com.projectschedule.ruby.repository.member.MemberRepository;
import com.projectschedule.ruby.repository.scheduleItem.ScheduleItemRepository;
import com.projectschedule.ruby.repository.scheduleItem.ScheduleItemRepositoryCustom;
import com.projectschedule.ruby.service.member.MemberService;
import com.projectschedule.ruby.service.schedule.ScheduleService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ScheduleItemServiceImplTest {

    @Autowired
    EntityManager em;
    JPAQueryFactory queryFactory;

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    ScheduleItemRepository scheduleItemRepository;

    @Autowired
    MemberService memberService;
    @Autowired
    ScheduleService scheduleService;
    @Autowired
    ScheduleItemService ScheduleItemService;

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

    /**
     * 스케쥴 세부목록 수정
     */
    @Test
    void modifyScheduleItem() {
        String email = "ruby8700@naver.com";
        String password = "12345678";

        Member member = new Member.Builder()
                .email(email)
                .password(password)
                .build();

        Member loginMember = memberService.loginMember(member);

        PageRequest pageRequest = PageRequest.of(0, 6);
        Page<Schedule> schedules = scheduleService.lookupScheduleList(loginMember, pageRequest);
        List<ScheduleItem> scheduleItemList = schedules.getContent().get(0).getScheduleItemList();

        assertThat(schedules.getSize()).isEqualTo(6);
        assertThat(scheduleItemList).extracting("itemName")
                .containsExactly("알고리즘 공부1", "알고리즘 공부2", "알고리즘 공부3", "알고리즘 공부4");


        for (ScheduleItem item : scheduleItemList) {
            item.modifyItemName("알고리즘").modifyProgress(100);
            ScheduleItemService.modifyScheduleItem(item);
        }

        em.flush();
        em.clear();

        schedules = scheduleService.lookupScheduleList(loginMember, pageRequest);
        scheduleItemList = schedules.getContent().get(0).getScheduleItemList();
        assertThat(scheduleItemList).extracting("itemName")
                .containsExactly("알고리즘", "알고리즘", "알고리즘", "알고리즘");
        assertThat(scheduleItemList).extracting("progress")
                .containsExactly(100, 100, 100, 100);
    }
}