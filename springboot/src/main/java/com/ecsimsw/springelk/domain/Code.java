package com.ecsimsw.springelk.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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

    public List<Variable> variableNames() {
        final Set<String> variableNames = new HashSet<>();
        final Pattern variablePattern = language.variablePattern();
        for (String line : content.split("\n")) {
            final Matcher matcher = variablePattern.matcher(line);
            if (matcher.find()) {
                variableNames.add(matcher.group());
            }
        }
        return variableNames.stream()
                .map(name -> new Variable(id, language, star, name))
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

    public String getClassName() {
        return className;
    }

    public Integer getStar() {
        return star;
    }

    public String getContent() {
        return content;
    }
}
