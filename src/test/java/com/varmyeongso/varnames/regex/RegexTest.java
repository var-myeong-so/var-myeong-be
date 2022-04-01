package com.varmyeongso.varnames.regex;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

public class RegexTest {

    public final String regex =
            "(?<=int |float |String |double )([a-zA-Z_]\\w*)(?=,|;|\\s)|([a-zA-Z_]\\w*)(?=,|;|\\s*=)";

    @DisplayName("변수명을 검색할 수 있는 정규식을 작성한다.")
    @ValueSource(strings = {
            "int h1 = 100;",
            "float h1 = 110;",
            "String h1 = 100;",
            "double h1 = 100;",
            "My_Class h1 = 100;",
            "Person h1 = 100;",
            "Code h1 = 100;"
    })
    @ParameterizedTest
    public void regexTest(String code) {
        final String varName = "h1";
        final Matcher matcher = Pattern.compile(regex).matcher(code);
        assertThat(matcher.find()).isTrue();
        assertThat(matcher.group()).isEqualTo(varName);
    }

    @DisplayName("변수가 없는 문장에선 정규식으로 변수명을 잡을 수 없다.")
    @ValueSource(strings = {
            "int h1 - 100;",
            "aaaa",
            "String h1 %%# 100;",
            "double = 100;"
    })
    @ParameterizedTest
    public void regexTest_nonExistence(String code) {
        final Matcher matcher = Pattern.compile(regex).matcher(code);
        assertThat(matcher.find()).isFalse();
    }
}
