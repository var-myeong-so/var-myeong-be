package com.ecsimsw.springelk.dto;

import com.ecsimsw.springelk.domain.Code;
import com.ecsimsw.springelk.domain.Language;

public class CodeFile {

    private final String className;
    private final String userName;
    private final String projectName;
    private final String packageName;
    private final Integer star;
    private final String path;
    private final Language language;
    private final String content;

    public CodeFile(String className, String userName, String projectName,
        String packageName, Integer star,
        String path,
        Language language, String content) {
        this.className = className;
        this.userName = userName;
        this.projectName = projectName;
        this.packageName = packageName;
        this.star = star;
        this.path = path;
        this.language = language;
        this.content = content;
    }

    public Code asCode() {
        return new Code(language, path, star, className, content);
    }

    public String getClassName() {
        return className;
    }

    public Integer getStar() {
        return star;
    }

    public String getPath() {
        return path;
    }

    public Language getLanguage() {
        return language;
    }

    public String getContent() {
        return content;
    }

    public String getUserName() {
        return userName;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getPackageName() {
        return packageName;
    }
}
