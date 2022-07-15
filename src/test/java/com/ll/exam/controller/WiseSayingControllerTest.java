package com.ll.exam.controller;

import com.ll.exam.App;
import com.ll.exam.AppTestRunner;
import com.ll.exam.Util;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WiseSayingControllerTest {
    // 모든 테스트는 앱 모드를 test로 변경
    @BeforeAll
    public void beforeAll() {
        App.mode = "test";
    }

    // 각 테스트 실행 전마다 수행
    @BeforeEach
    public void beforeEach() {
        Util.file.deleteDir(App.getDefaultPath());   // test 디렉토리 삭제
    }

    @Test
    public void 프로그램이_시작하면_시작문구_출력() {
        String s = AppTestRunner.run("종료");
        // 프로그램 시작이되었을 때 문구가 잘 출력되는지 검증
        assertTrue(s.contains("== 명언 SSG =="));
        assertTrue(s.contains("명령)"));
    }

    @Test
    public void 등록_입력시_명언과_작가를_입력받기() {
        String s = AppTestRunner.run("""
                등록
                나의 죽음을 적에게 알리지 마라
                이순신
                종료
                """);

        // 등록을 입력했을 때, 명언과 작가를 입력받는 문구가 출력되는지 검증
        assertTrue(s.contains("명언 : "));
        assertTrue(s.contains("작가 : "));
    }

    @Test
    public void 등록시_자동으로_증가된_번호를_출력하기() {
        String s = AppTestRunner.run("""
                등록
                나의 죽음을 적에게 알리지 마라
                이순신
                등록
                나에게 불가능이란 없다
                나폴레옹
                종료
                """);
        // 명언, 작가 입력을 하고 등록을 했을 때, 자동으로 증가된 명언 번호가 출력되는지 검증
        assertTrue(s.contains("1번 명언이 등록되었습니다."));
        assertTrue(s.contains("2번 명언이 등록되었습니다."));
    }

    @Test
    public void 목록_입력시_목록문구와_데이터_출력() {
        String s = AppTestRunner.run("""
                등록
                나의 죽음을 적에게 알리지 마라
                이순신
                등록
                나에게 불가능이란 없다
                나폴레옹
                목록
                종료
                """);
        // 목록 입력시 등록 문구와 해당 데이터가 잘 출력되는지 검증
        assertTrue(s.contains("번호 / 작가 / 명언"));
        assertTrue(s.contains("----------------------"));
        // 역순으로 명언이 출력되었는지 검증
        assertTrue(s.contains("""
        2 / 나폴레옹 / 나에게 불가능이란 없다
        1 / 이순신 / 나의 죽음을 적에게 알리지 마라
        """));
    }

    @Test
    public void 명언_삭제() {
        String s = AppTestRunner.run("""
                등록
                나의 죽음을 적에게 알리지 마라
                이순신
                등록
                나에게 불가능이란 없다
                나폴레옹
                목록
                삭제?id=1
                목록
                종료
                """);
        // 1번 명언이 제대로 삭제되었는지 검증
        assertTrue(s.contains("1번 명언이 삭제되었습니다."));
        assertTrue(s.contains("2 / 나폴레옹 / 나에게 불가능이란 없다"));
    }

    @Test
    public void 존재하지_않는_명언삭제에_대한_예외처리() {
        String s = AppTestRunner.run("""
                등록
                나의 죽음을 적에게 알리지 마라
                이순신
                등록
                나에게 불가능이란 없다
                나폴레옹
                목록
                삭제?id=3
                종료
                """);
        // 3번 명언은 삭제할 수 없다는 예외 문구가 출력되는지 검증
        assertTrue(s.contains("3번 명언은 존재하지 않습니다."));
    }

    @Test
    public void 잘못된_형식의_명언_삭제요청에_대한_예외처리() {
        String s = AppTestRunner.run("""
                등록
                나의 죽음을 적에게 알리지 마라
                이순신
                삭제
                종료
                """);
        // 잘못된 형식으로 삭제 요청을 했을 때 정정 문구가 출력되는지 검증
        assertTrue(s.contains("삭제?id=value 형식으로 입력해주세요."));
    }

    @Test
    public void 수정_입력시_재입력문구_출력후_명언수정() {
        String s = AppTestRunner.run("""
                등록
                나의 죽음을 적에게 알리지 마라
                이순신
                등록
                나에게 불가능이란 없다
                나폴레옹
                수정?id=1
                늦었다고 생각할 때가 진짜 늦었다
                박명수
                목록
                종료
                """);
        // 1번 명언이 잘 수정되었는지 검증
        assertTrue(s.contains("명언(기존) : 나의 죽음을 적에게 알리지 마라"));
        assertTrue(s.contains("명언 : "));
        assertTrue(s.contains("작가(기존) : 이순신"));
        assertTrue(s.contains("작가 : "));
        assertTrue(s.contains("1 / 박명수 / 늦었다고 생각할 때가 진짜 늦었다"));
    }

    @Test
    public void 존재하지_않는_명언수정에_대한_예외처리() {
        String s = AppTestRunner.run("""
                등록
                나의 죽음을 적에게 알리지 마라
                이순신
                등록
                나에게 불가능이란 없다
                나폴레옹
                목록
                수정?id=3
                종료
                """);
        // 3번 명언은 수정할 수 없다는 예외 문구가 출력되는지 검증
        assertTrue(s.contains("3번 명언은 존재하지 않습니다."));
    }

    @Test void 잘못된_형식의_명언_수정요청에_대한_예외처리() {
        String s = AppTestRunner.run("""
                등록
                나의 죽음을 적에게 알리지 마라
                이순신
                수정
                종료
                """);
        // 잘못된 형식으로 수정 요청을 했을 때 정정 문구가 출력되는지 검증
        assertTrue(s.contains("수정?id=value 형식으로 입력해주세요."));
    }
}