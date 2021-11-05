//package com.projectschedule.ruby;
//
//import com.projectschedule.ruby.entity.Member;
//import com.projectschedule.ruby.entity.Schedule;
//import com.projectschedule.ruby.entity.ScheduleItem;
//import com.projectschedule.ruby.entity.enumItem.ProgressStatus;
//import com.projectschedule.ruby.entity.enumItem.ScheduleKind;
//import com.projectschedule.ruby.repository.member.MemberRepository;
//import com.projectschedule.ruby.service.schedule.ScheduleService;
//import com.projectschedule.ruby.service.scheduleItem.ScheduleItemService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.stereotype.Component;
//
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
//            String prefix = null;
//            ScheduleKind kind = ScheduleKind.STUDY;
//            if (i % 4 == 0) {
//                prefix = "알고리즘";
//                kind = ScheduleKind.STUDY;
//            } else if (i % 4 == 1) {
//                prefix = "악기연습";
//                kind = ScheduleKind.HOBBY;
//            } else if (i % 4 == 2) {
//                prefix = "맛집탐방";
//                kind = ScheduleKind.TRAVEL;
//            } else if (i % 4 == 3) {
//                prefix = "휴식";
//                kind = ScheduleKind.ECT;
//            }
//
//            Schedule schedule = new Schedule.Builder()
//                    .scheduleName(prefix + i)
//                    .startDay(startDay)
//                    .endDay(endDay)
//                    .kind(kind)
//                    .status(ProgressStatus.PROCEED)
//                    .member(member)
//                    .build();
//
//            scheduleService.addSchedule(schedule);
//
//            for (int j = 0; j < 4; j++) {
//                ScheduleItem scheduleItem = new ScheduleItem.Builder()
//                        .itemName(prefix + j)
//                        .progress(15 * j)
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
