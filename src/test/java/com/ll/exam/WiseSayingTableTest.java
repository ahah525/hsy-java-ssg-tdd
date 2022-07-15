

package com.ll.exam;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class WiseSayingTableTest {
    @Test
    public void 저장() {
        WiseSayingTable wiseSayingTable = new WiseSayingTable("test_data");
        wiseSayingTable.save("나의 죽음을 적에게 알리지 마라", "이순신");
        wiseSayingTable.save("나에게 불가능이란 없다", "나폴레옹");
        // 위에서 저장한 파일명이 자동 넘버링 되는지 검증
        assertTrue(new File("test_data/wise_saying/1.json").exists());
        assertTrue(new File("test_data/wise_saying/2.json").exists());
    }
}