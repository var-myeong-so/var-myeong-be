package com.varmyeongso.varnames.regex;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

public class RegexTest {

    public final String regex = "(?<=int |float |String |double )([a-zA-Z_]\\w*)(?=,|;|\\s)|([a-zA-Z_]\\w*)(?=,|;|\\s*=)";

    public final String r1= "(?<=int |float |String |double )([a-zA-Z_]\\w*)(?=,|;|\\s)|([a-zA-Z_]\\w*)(?=,|;|\\s*=)";
    public final String r2 = "[A-Z][a-zA-Z0-9_]*";
    // '대문자 시작 영단어 ' [단어하나] ';' or ' =' or '='


    @DisplayName("대문자 시작, 영어 + 숫자 + _을 포함하는 단어 확인")
    @Test
    public void variableNameRule() {
        final String varName = "Heee324_";
        final Matcher matcher = Pattern.compile("[A-Z][a-zA-Z0-9_]*").matcher("Heee324_");
        assertThat(matcher.find()).isTrue();
        assertThat(matcher.group()).isEqualTo(varName);
    }

    @DisplayName(" 대문자 시작, 영어 + 숫자 + _을 포함하는 단어의 공백 뒷부분을 찾는다.")
    @Test
    public void afterBlank() {
        final String varName = "hidd";
        final String regex = "[A-Z][a-zA-Z0-9_][\\s]([a-zA-Z0-9_]*)(?=[\\s;=])";
        final Matcher matcher = Pattern.compile(regex).matcher("Isdf9 number = 54;");
        assertThat(matcher.find()).isTrue();
        assertThat(matcher.group()).isEqualTo(varName);
    }

    @DisplayName("어떤 단어 뒤가 ';' 또는 '=' 또는 ' =' 인지 확인")
    @Test
    public void nextWord() {
        final String varName = "Heee324_";
        final Matcher matcher = Pattern.compile(".+(=?[;|\\$=])").matcher("Heee324_=");
        assertThat(matcher.find()).isTrue();
        assertThat(matcher.group()).isEqualTo(varName);
    }


    @Test
    public void tempTest() {
        final String varName = "Heee324";
        final Matcher matcher = Pattern.compile(r2).matcher("Heee324");
        assertThat(matcher.find()).isTrue();
        assertThat(matcher.group()).isEqualTo(varName);
    }

    @DisplayName("변수명을 검색할 수 있는 정규식을 작성한다.")
    @ValueSource(strings = {
            "int h1 = 100;",
            "float h1 = 110;",
            "String h1 = 100;",
            "double h1 = 100;",
            "Mysss h1 = 100;",
            "Person h1 = 100;",
            "Code h1 = 100;"
    })
    @ParameterizedTest
    public void regexTest(String code) {
        final String varName = "h1";
        final Matcher matcher = Pattern.compile(r2).matcher(code);
        assertThat(matcher.find()).isTrue();
        assertThat(matcher.group()).isEqualTo(varName);
    }

    @DisplayName("변수가 없는 문장에선 정규식으로 변수명을 잡을 수 없다.")
    @ValueSource(strings = {
            "int h1 - 100;",
            "h2(sadf)",
            "String h1 %%# 100;",
            "double = 100;"
    })
    @ParameterizedTest
    public void regexTest_nonExistence(String code) {
        final Matcher matcher = Pattern.compile(r2).matcher(code);
        assertThat(matcher.find()).isFalse();
    }
}
