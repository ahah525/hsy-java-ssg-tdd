package com.ll.exam;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class AppTest {
    @Test
    public void 테스트() {
        assertTrue(1 == 1);
        assertThat(1).isEqualTo(1);
    }
}
