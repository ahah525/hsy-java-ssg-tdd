package com.ll.exam;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class AppTest {
    @Test
    public void 테스트() {
        assertTrue(1 == 1);
        assertThat(1).isEqualTo(1);
    }

    @Test
    public void 키보드가_아닌_문자열을_스캐너에_입력하기() {
        Scanner sc = TestUtil.genScanner("입력");
        String s = sc.nextLine().trim();    // 앞 뒤 공백 제거
        // 스캐너에 입력받은 문자열이 "입력"이 맞는지 검증
        assertThat(s).isEqualTo("입력");
    }

    @Test
    public void 출력을_모니터가_아닌_문자열로_반환하기() {
        // 모니터에 출력할 문자열이 모두 output에 담김
        ByteArrayOutputStream output = TestUtil.setOutToByteArray();
        System.out.print("출력");
        String s = output.toString();
        // 출력 방향을 표준 출력으로 변경
        TestUtil.clearSetOutToByteArray(output);
        // 출력된 문자열이 "출력"이 맞는지 검증
        assertThat(s).isEqualTo("출력");
    }

    @Test
    public void 문자열_파일에_저장하기() {
        // 디렉토리 생성 후, 해당 path 파일에 body 쓰기
        Util.file.mkdir("test_data");
        Util.file.saveToFile("test_data/1.txt", "데이터");
        String body = Util.file.readFromFile("test_data/1.txt");
        // 파일에 저장한 문자열이 실제 파일에서 읽어온 문자열과 동일한지 검증
        assertThat(body).isEqualTo("데이터");
    }

    @Test
    public void 프로그램이_시작하면_시작문구_출력() {
        Scanner sc = TestUtil.genScanner("종료");
        ByteArrayOutputStream output = TestUtil.setOutToByteArray();

        new App(sc).run();

        String s = output.toString();
        TestUtil.clearSetOutToByteArray(output);
        // 프로그램 시작이되었을 때 문구가 잘 출력되는지 검증
        assertTrue(s.contains("== 명언 SSG =="));
        assertTrue(s.contains("명령)"));
    }
}
