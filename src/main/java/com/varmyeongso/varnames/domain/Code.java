package com.varmyeongso.varnames.domain;

import java.util.List;
import javax.persistence.*;

@Entity
public class Code {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private String projectName;
    private String packageName;
    private String className;
    private String language;
    private String link;

    @Lob
    private String code;

    public Code() {
    }

    public Code(String projectName, String packageName, String className, String language, String link, String code) {
        this.projectName = projectName;
        this.packageName = packageName;
        this.className = className;
        this.language = language;
        this.link = link;
        this.code = code;
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

    public List<String> parse(String target) {
        return null;
    }
}
