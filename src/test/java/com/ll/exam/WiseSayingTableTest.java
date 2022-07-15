

package com.ll.exam;

import com.ll.exam.domain.WiseSaying;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;
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
        wiseSayingTable.save("나의 죽음을 적에게 알리지 마라", "이순신");
        wiseSayingTable.save("나에게 불가능이란 없다", "나폴레옹");
    }
    @Test
    public void 명언_객체를_파일에_저장() {
        int newId = wiseSayingTable.getLastId() + 1;    // 새로 저장할 명언의 id
        // 해당 객체를 파일로 저장
        wiseSayingTable.save("선을 행하는 데는 생각이 필요 없다", "괴테");
        // 위에서 저장한 파일이 존재하는지 검증
        assertTrue(new File("test_data/wise_saying/%d.json".formatted(newId)).exists());
    }

    @Test
    public void 파일을_읽어_명언_객체_조회() {
        // 1번 명언 파일에서 조회하기
        WiseSaying wiseSaying = wiseSayingTable.findById(2);
        // 파일에 저장된 것을 읽어온 데이터가 위에서 저장된 2번 명언 정보와 동일한지 검증
        assertThat(wiseSaying.getId()).isEqualTo(2);
        assertThat(wiseSaying.getContent()).isEqualTo("나에게 불가능이란 없다");
        assertThat(wiseSaying.getAuthor()).isEqualTo("나폴레옹");
    }
}