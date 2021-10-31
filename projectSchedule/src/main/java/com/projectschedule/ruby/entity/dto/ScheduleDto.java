package com.projectschedule.ruby.entity.dto;

import com.projectschedule.ruby.entity.enumItem.ProgressStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import static javax.persistence.EnumType.STRING;

@Getter
@Setter
public class ScheduleDto {

    private Long id;
    private String scheduleName;            // 스케쥴 명
    private LocalDate startDay;             // 시작일
    private LocalDate endDay;               // 종료일
    private ProgressStatus status;          // 진행상태
}
