package com.projectschedule.ruby.entity.enumItem;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProgressStatusTest {

    /**
     * enum 타입에 설정한 모든 종류 확인
     */
    @Test
    void getStatusKinds() {
        ProgressStatus[] enumConstants = ProgressStatus.class.getEnumConstants();

        for (ProgressStatus enumConstant : enumConstants) {
            System.out.println(enumConstant);
        }
    }
}