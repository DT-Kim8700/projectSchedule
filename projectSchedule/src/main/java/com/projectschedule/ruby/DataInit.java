//package com.projectschedule.ruby;
//
//import com.projectschedule.ruby.entity.Member;
//import com.projectschedule.ruby.entity.Schedule;
//import com.projectschedule.ruby.entity.ScheduleItem;
//import com.projectschedule.ruby.entity.enumItem.ProgressStatus;
//import com.projectschedule.ruby.entity.enumItem.ScheduleKind;
//import com.projectschedule.ruby.repository.member.MemberRepository;
//import com.projectschedule.ruby.repository.schedule.ScheduleRepository;
//import com.projectschedule.ruby.service.schedule.ScheduleService;
//import com.projectschedule.ruby.service.scheduleItem.ScheduleItemService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.stereotype.Component;
//
//import javax.persistence.EntityManager;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//
//@Component
//@RequiredArgsConstructor
//public class DataInit implements ApplicationRunner {
//
//    private final MemberRepository memberRepository;
//    private final ScheduleService scheduleService;
//    private final ScheduleItemService scheduleItemService;
//
//    public void init() {
//        Member member = new Member.Builder()
//                .email("ruby8700@naver.com")
//                .name("ruby")
//                .password("12345678")
//                .build();
//
//        memberRepository.save(member);
//
//        for (int i = 1; i <= 30; i++) {
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//
//            LocalDate startDay = LocalDate.parse("2021-10-11", formatter);
//            LocalDate endDay = LocalDate.parse("2021-10-21", formatter);
//
//            Schedule schedule = new Schedule.Builder()
//                    .scheduleName("스케쥴" + i)
//                    .startDay(startDay)
//                    .endDay(endDay)
//                    .kind(ScheduleKind.STUDY)
//                    .status(ProgressStatus.PROCEED)
//                    .member(member)
//                    .build();
//
//            scheduleService.addSchedule(schedule);
//        }
//
//        PageRequest pageRequest = PageRequest.of(0, 6);
//        Page<Schedule> schedules = scheduleService.lookupScheduleList(member, pageRequest);
//
//        for (Schedule schedule : schedules) {
//            for (int i = 1; i <= 4; i++) {
//                ScheduleItem scheduleItem = new ScheduleItem.Builder()
//                        .itemName("알고리즘 공부" + i)
//                        .progress(15 * i)
//                        .schedule(schedule)
//                        .build();
//
//                scheduleItemService.addScheduleItem(scheduleItem);
//            }
//        }
//    }
//
//    /**
//     * Callback used to run the bean.
//     *
//     * @param args incoming application arguments
//     * @throws Exception on error
//     */
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        init();
//    }
//}
