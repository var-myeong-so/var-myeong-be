package com.ecsimsw.springelk.dto;

import com.ecsimsw.springelk.domain.Code;
import com.ecsimsw.springelk.domain.Language;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CodeResponse {

    private String id;
    private Language language;
    private String path;
    private Integer star;
    private String className;
    private String content;

    public CodeResponse(String id, Language language, String path, Integer star, String className, String content) {
        this.id = id;
        this.language = language;
        this.path = path;
        this.star = star;
        this.className = className;
        this.content = content;
    }

    public static CodeResponse of(Code code) {
        return new CodeResponse(
                code.id(),
                code.language(),
                code.path(),
                code.star(),
                code.className(),
                code.content()
        );
    }

    public static List<CodeResponse> listOf(List<Code> codes) {
        return codes.stream()
                .map(CodeResponse::of)
                .collect(Collectors.toList());
    }

    public String getId() {
        return id;
    }

    public Language getLanguage() {
        return language;
    }

    public String getPath() {
        return path;
    }

    public Integer getStar() {
        return star;
    }

    public String getClassName() {
        return className;
    }

    public String getContent() {
        return content;
    }
}
