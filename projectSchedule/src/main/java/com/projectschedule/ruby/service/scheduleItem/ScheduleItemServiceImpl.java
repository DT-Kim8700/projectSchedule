package com.projectschedule.ruby.service.scheduleItem;

import com.projectschedule.ruby.entity.Schedule;
import com.projectschedule.ruby.entity.ScheduleItem;
import com.projectschedule.ruby.repository.scheduleItem.ScheduleItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleItemServiceImpl implements ScheduleItemService{

    private final ScheduleItemRepository scheduleItemRepository;

    /**
     * 스케쥴 세부목록 등록
     *
     * @param scheduleItem
     * @return
     */
    @Override
    public void addScheduleItem(ScheduleItem scheduleItem) {
        scheduleItemRepository.save(scheduleItem);
    }

    /**
     * 스케쥴 세부목록 갱신
     *
     * @param scheduleItem
     * @return
     */
    @Override
    public void modifyScheduleItem(ScheduleItem scheduleItem) {
        ScheduleItem findScheduleItem = scheduleItemRepository.findById(scheduleItem.getId()).orElseGet(null);
        if (findScheduleItem != null) {
            findScheduleItem.modifyItemName(scheduleItem.getItemName())
                    .modifyProgress(scheduleItem.getProgress());
        }
    }

    /**
     * 스케쥴 세부목록 삭제
     *
     * @param scheduleItem
     * @return
     */
    @Override
    public void removeScheduleItem(ScheduleItem scheduleItem) {
        scheduleItemRepository.delete(scheduleItem);
    }
}
