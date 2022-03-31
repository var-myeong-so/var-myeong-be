package com.varmyeongso.varnames.regex;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

public class RegexTest {

    @DisplayName("한정된 타입의 변수명을 검색할 수 있는 정규식을 작성한다.")
    @Test
    public void regexTest() {

        List<CodeAndVar> codeAndVars = List.of(
                new CodeAndVar("int a = 100;", "a"),
                new CodeAndVar("float b = 110;", "b"),
                new CodeAndVar("String hi2 = 100;", "hi2"),
                new CodeAndVar("double ad = 100;", "ad")
        );

        final String regex = "(?<=int |float |String |double )([a-zA-Z_]\\w*)(?=,|;|\\s)|([a-zA-Z_]\\w*)(?=,|;|\\s*=)";

        for(CodeAndVar codeAndVar : codeAndVars) {
            final Matcher matcher = Pattern.compile(regex).matcher(codeAndVar.code);
            while(matcher.find()) {
                final String group = matcher.group();
                assertThat(group).isEqualTo(codeAndVar.var);
            }
        }
    }
}

class CodeAndVar {
    public String code;
    public String var;

    public CodeAndVar(String code, String var) {
        this.code = code;
        this.var = var;
    }
}
