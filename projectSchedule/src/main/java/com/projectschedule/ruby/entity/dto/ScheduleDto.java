package com.projectschedule.ruby.entity.dto;

import com.projectschedule.ruby.entity.enumItem.ProgressStatus;
import com.projectschedule.ruby.entity.enumItem.ScheduleKind;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

import static javax.persistence.EnumType.STRING;

@Getter
@Setter
@NoArgsConstructor
public class ScheduleDto {

    private Long id;
    @NotNull @NotEmpty
    private String scheduleName;            // 스케쥴 명
    @NotNull @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDay;             // 시작일
    @NotNull @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDay;               // 종료일
    @NotNull @Enumerated(STRING)
    private ScheduleKind kind;              // 스케쥴 종류
    @NotNull @Enumerated(STRING)
    private ProgressStatus status;          // 진행상태

    private List<ScheduleItemDto> scheduleItemList;    // 세부 스케쥴 목록
}

