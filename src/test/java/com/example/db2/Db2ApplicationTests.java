package com.example.db2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class Db2ApplicationTests {

    @Test
    @DisplayName("String 테스트")
    void string() {
        String emptyStr = "";
        String blankStr = "     ";

        assertThat(StringUtils.hasText(emptyStr)).isFalse();
        assertThat(StringUtils.hasText(blankStr)).isFalse();
        assertThat(ObjectUtils.isEmpty(emptyStr)).isTrue();
        assertThat(ObjectUtils.isEmpty(blankStr)).isFalse();
    }

    @Test
    @DisplayName("Optional 테스트")
    void optional() {
        String nullStr = null;
        String str = "zxcv";

        assertThatThrownBy(() -> Optional.ofNullable(nullStr).orElseThrow()).isInstanceOf(NoSuchElementException.class);
        assertThat(Optional.ofNullable(str).orElseThrow()).isEqualTo(str);
    }
}
