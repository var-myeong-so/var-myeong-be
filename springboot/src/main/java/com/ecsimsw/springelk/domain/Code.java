package com.ecsimsw.springelk.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
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
        for (String line : contentLines()) {
            final Matcher matcher = variablePattern().matcher(line);
            if (matcher.find()) {
                variableNames.add(matcher.group());
            }
        }
        return variableNames.stream()
                .map(name -> new Variable(id, language, star, name))
                .collect(Collectors.toList());
    }

    public Integer firstPositionOf(Variable variable) {
        final List<String> lines = contentLines();
        for (int index = 0; index < lines.size(); index++) {
            final String line = lines.get(index);
            if (line.contains(variable.name()) && variablePattern().matches(line)) {
                return index;
            }
        }
        throw new IllegalArgumentException("Unmatched variable");
    }

    public Integer firstPositionOf(String name) {
        final List<String> lines = contentLines();
        for (int index = 0; index < lines.size(); index++) {
            final String line = lines.get(index);
            if (line.toLowerCase().contains(name.toLowerCase())) {
                return index;
            }
        }
        throw new IllegalArgumentException("Unmatched variable");
    }

    public List<String> contentLines() {
        return List.of(content.split("\n"));
    }

    public String id() {
        return id;
    }

    public Language language() {
        return language;
    }

    public String path() {
        return path;
    }

    public String className() {
        return className;
    }

    public Integer star() {
        return star;
    }

    public String content() {
        return content;
    }

    @Transient
    public VariablePattern variablePattern() {
        return VariablePattern.of(this.language);
    }

    public Integer classIndex() {
        List<String> lines = contentLines();
        for (String line : lines) {
            if (ClassPattern.JAVA.matches(line)) {
                return lines.indexOf(line);
            }
        }
        throw new IllegalArgumentException("Unmatched class");
    }
}
