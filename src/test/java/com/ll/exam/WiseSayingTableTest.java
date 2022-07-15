package com.ll.exam;

import com.ll.exam.domain.WiseSaying;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.File;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WiseSayingTableTest {

    private WiseSayingTable wiseSayingTable;

    // 모든 테스트는 앱 모드를 test로 변경
    @BeforeAll
    public void beforeAll() {
        App.mode = "test";
        wiseSayingTable = new WiseSayingTable(App.getDefaultPath());    //
    }

    // 각 테스트 실행 전마다 수행
    @BeforeEach
    public void beforeEach() {
        Util.file.deleteDir(App.getDefaultPath());   // test 디렉토리 삭제
        // 명언 2개 저장
        wiseSayingTable.save("나의 죽음을 적에게 알리지 마라", "이순신");
        wiseSayingTable.save("나에게 불가능이란 없다", "나폴레옹");
    }

    @Test
    public void 데이터_저장() {
        int newId = wiseSayingTable.getLastId() + 1;    // 새로 저장할 명언의 id
        // 해당 객체를 파일로 저장
        wiseSayingTable.save("선을 행하는 데는 생각이 필요 없다", "괴테");
        // 위에서 저장한 파일이 존재하는지 검증
        assertTrue(new File(App.getDefaultPath() + "/wise_saying/%d.json".formatted(newId)).exists());
    }

    @Test
    public void 데이터_조회() {
        // 1번 명언 파일에서 조회하기
        WiseSaying wiseSaying = wiseSayingTable.findById(2);
        // 파일에 저장된 것을 읽어온 데이터가 위에서 저장된 2번 명언 정보와 동일한지 검증
        assertThat(wiseSaying.getId()).isEqualTo(2);
        assertThat(wiseSaying.getContent()).isEqualTo("나에게 불가능이란 없다");
        assertThat(wiseSaying.getAuthor()).isEqualTo("나폴레옹");
    }

    @Test
    public void 데이터_전체조회() {
        List<WiseSaying> wiseSayings = wiseSayingTable.findAll();
        // 파일에 저장된 것을 읽어온 데이터가 위에서 저장된 1번 명언 정보와 동일한지 검증
        WiseSaying ws1 = wiseSayings.get(0);
        assertThat(ws1.getId()).isEqualTo(1);
        assertThat(ws1.getContent()).isEqualTo("나의 죽음을 적에게 알리지 마라");
        assertThat(ws1.getAuthor()).isEqualTo("이순신");
        // 파일에 저장된 것을 읽어온 데이터가 위에서 저장된 2번 명언 정보와 동일한지 검증
        WiseSaying ws2 = wiseSayings.get(1);
        assertThat(ws2.getId()).isEqualTo(2);
        assertThat(ws2.getContent()).isEqualTo("나에게 불가능이란 없다");
        assertThat(ws2.getAuthor()).isEqualTo("나폴레옹");
    }

    @Test
    public void 데이터_삭제() {
        wiseSayingTable.removeById(1);
        WiseSaying wiseSaying = wiseSayingTable.findById(1);
        // 1번 명언 삭제 후, 파일에서 삭제됐는지 검증
        assertThat(wiseSaying).isEqualTo(null);
    }
}