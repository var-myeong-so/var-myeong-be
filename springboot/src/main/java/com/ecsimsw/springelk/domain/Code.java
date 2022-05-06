package com.ecsimsw.springelk.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Document(indexName = "code")
public class Code {

    @Id
    private String id;

    @Field(type = FieldType.Keyword)
    private Language language;

    @Field(type = FieldType.Keyword)
    private String path;

    @Field(type = FieldType.Integer)
    private Integer star;

    @Field(type = FieldType.Keyword)
    private String className;

    @Field(type = FieldType.Text)
    private String content;

    public Code() {
    }

    public Code(Language language, String path, Integer star, String className, String content) {
        this.language = language;
        this.path = path;
        this.star = star;
        this.className = className;
        this.content = content;
    }

    public List<VariableName> variableNames() {
        final List<VariableName> variableNames = new ArrayList<>();
        final Pattern variablePattern = language.variablePattern();
        for (String line : content.split("\n")) {
            final Matcher matcher = variablePattern.matcher(line);
            if (matcher.find()) {
                variableNames.add(new VariableName(id, language, star, matcher.group()));
            }
        }
        return variableNames;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
