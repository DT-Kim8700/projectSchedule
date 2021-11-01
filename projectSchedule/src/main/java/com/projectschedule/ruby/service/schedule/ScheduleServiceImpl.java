package com.projectschedule.ruby.service.schedule;

import com.projectschedule.ruby.entity.Member;
import com.projectschedule.ruby.entity.Schedule;
import com.projectschedule.ruby.repository.schedule.ScheduleRepository;
import com.projectschedule.ruby.repository.scheduleItem.ScheduleItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleServiceImpl implements ScheduleService{

    private final ScheduleRepository scheduleRepository;
    private final ScheduleItemRepository scheduleItemRepository;

    /**
     * 스케쥴 목록 조회 V2
     *
     * @param member
     * @param pageable
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Schedule> lookupScheduleList(Member member, Pageable pageable) {
        Page<Schedule> schedules = scheduleRepository.selectScheduleByMember(member, pageable);

        for (Schedule schedule : schedules) {
            schedule.getScheduleItemList();
        }

        return schedules;
    }

    /**
     * 스케쥴 등록
     *
     * @param schedule
     * @return
     */
    @Override
    public void addSchedule(Schedule schedule) {
        scheduleRepository.save(schedule);
    }

    /**
     * 스케쥴 갱신
     *
     * @param schedule
     * @return
     */
    @Override
    public void modifySchedule(Schedule schedule) {
        Schedule findSchedule = scheduleRepository.findById(schedule.getId()).orElseGet(null);
        if (findSchedule != null) {
            findSchedule.modifyScheduleName(schedule.getScheduleName())
                    .modifyStartDay(schedule.getStartDay())
                    .modifyEndDay(schedule.getEndDay())
                    .modifyStatus(schedule.getStatus());
        }
        // 변경감지를 통해서 수정한다. 먼저 이전 값을 조회해와서 모든 필드값을 채운 영속 객체를 얻은 다음 변경된 값만 변경 적용
        // 그렇지 않으면 채우지 않은 필드가 null 로 반영이 될 위험이 있다.
    }

    /**
     * 스케쥴 삭제 - 하위 ScheduleItem 도 모두 삭제
     *
     * @param schedule
     * @return
     */
    @Override
    public void removeSchedule(Schedule schedule) {
        scheduleItemRepository.deleteScheduleItemBySchedule(schedule);
        scheduleRepository.delete(schedule);
    }
}
