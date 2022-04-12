package com.varmyeongso.varnames.dto;

import com.varmyeongso.varnames.domain.Code;

import java.util.List;

public class CodeResponse {

    private final Long id;
    private final String projectName;
    private final String packageName;
    private final String className;
    private final String language;
    private final String link;
    private final String code;

    public CodeResponse(
            Long id,
            String projectName,
            String packageName,
            String className,
            String language,
            String link,
            String code) {
        this.id = id;
        this.projectName = projectName;
        this.packageName = packageName;
        this.className = className;
        this.language = language;
        this.link = link;
        this.code = code;
    }

    public static CodeResponse of(Code code) {
        return new CodeResponse(
                code.getId(),
                code.getProjectName(),
                code.getPackageName(),
                code.getClassName(),
                code.getLanguage(),
                code.getLink(),
                code.getCode()
        );
    }

    public static CodeResponses listOf(List<CodeResponse> codeResponses) {
        return new CodeResponses(codeResponses);
    }

    public Long getId() {
        return id;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getClassName() {
        return className;
    }

    public String getLanguage() {
        return language;
    }

    public String getLink() {
        return link;
    }

    public String getCode() {
        return code;
    }
}
