package com.projectschedule.ruby.service.schedule;

import com.projectschedule.ruby.entity.Schedule;
import com.projectschedule.ruby.entity.dto.ScheduleDto;
import com.projectschedule.ruby.entity.enumItem.ProgressStatus;
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
     * 스케쥴 목록 조회
     *
     * @param memberId
     * @param pageable
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Schedule> lookupScheduleList(Long memberId, Pageable pageable) {
        return scheduleRepository.selectScheduleByMember(memberId, pageable);
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
     * @param scheduleDto
     * @return
     */
    @Override
    public void modifySchedule(ScheduleDto scheduleDto) {
        Schedule fineSchedule = scheduleRepository.findById(scheduleDto.getId()).orElseGet(null);
        if (fineSchedule != null) {
            fineSchedule.modifyScheduleName("스케쥴변경")
                    .modifyStartDay(scheduleDto.getStartDay())
                    .modifyEndDay(scheduleDto.getEndDay())
                    .modifyStatus(scheduleDto.getStatus());
        }

        // 변경감지를 통해서 수정한다. 먼저 이전 값을 조회해와서 모든 필드값을 채운 영속 객체를 얻은 다음 변경된 값만 변경 적용
        // 그렇지 않으면 채우지 않은 필드가 null 로 반영이 될 위험이 있다.
    }

    /**
     * 스케쥴 삭제 - 하위 ScheduleItem 도 모두 삭제
     *
     * @param scheduleId
     * @return
     */
    @Override
    public void removeSchedule(Long scheduleId) {
        scheduleItemRepository.deleteScheduleItemBySchedule(scheduleId);
        scheduleRepository.deleteById(scheduleId);
    }
}
