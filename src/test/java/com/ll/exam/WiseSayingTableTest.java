

package com.ll.exam;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class WiseSayingTableTest {

    private WiseSayingTable wiseSayingTable;

    public WiseSayingTableTest() {
        // 기본 path를 test_data 로 지정
        wiseSayingTable = new WiseSayingTable("test_data");
    }

    // 각 테스트 실행 전마다 수행
    @BeforeEach
    public void beforeEach() {
        Util.file.deleteDir("test_data");   // test_data 디렉토리 삭제
        // 명언 2개 저장
        wiseSayingTable.save("나에게 불가능이란 없다.", "나폴레옹");
        wiseSayingTable.save("나의 죽음을 적들에게 알리지 마라.", "이순신");
    }
    @Test
    public void 저장() {
        int newId = wiseSayingTable.getLastId() + 1;    // 새로 저장할 명언의 id
        // 해당 객체를 파일로 저장
        wiseSayingTable.save("선을 행하는 데는 생각이 필요 없다", "괴테");
        // 위에서 저장한 파일이 존재하는지 검증
        assertTrue(new File("test_data/wise_saying/%d.json".formatted(newId)).exists());
    }
}