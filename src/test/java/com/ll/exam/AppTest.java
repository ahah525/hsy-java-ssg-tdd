package com.ll.exam;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
        // 모니터에 출력할 문자열이 모두 output에 담김)
        ByteArrayOutputStream output = TestUtil.setOutToByteArray();
        System.out.print("출력");
        String s = output.toString();
        TestUtil.clearSetOutToByteArray(output);
        // 출력된 문자열이 "출력"이 맞는지 검증
        assertThat(s).isEqualTo("출력");
    }
}
