package com.projectschedule.ruby.service.scheduleItem;

import com.projectschedule.ruby.entity.Member;
import com.projectschedule.ruby.entity.Schedule;
import com.projectschedule.ruby.entity.ScheduleItem;
import com.projectschedule.ruby.service.TestSeed;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ScheduleItemServiceImplTest extends TestSeed {

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

        Member loginMember = memberService.loginMember(email, password);

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