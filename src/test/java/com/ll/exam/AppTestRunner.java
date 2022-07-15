package com.ll.exam;

import java.io.ByteArrayOutputStream;
import java.util.Scanner;

public class AppTestRunner {
    public static String run(String input) {
        // 스캐너에 인자로 받은 문자열 입력하기
        Scanner sc = TestUtil.genScanner(input);
        // 출력 방향을 output으로 변경
        ByteArrayOutputStream output = TestUtil.setOutToByteArray();
        // 명언 프로그램 실행
        new App(sc).run();
        // 출력된 모든 문자열
        String s = output.toString();
        // 출력 방향을 표준 출력으로 변경
        TestUtil.clearSetOutToByteArray(output);

        return s;
    }
}
