package com.ecsimsw.springelk.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Objects;

@Document(indexName = "variable")
public class Variable {

    @Id
    private String id;

    @Field(type = FieldType.Keyword)
    private String codeId;

    @Field(type = FieldType.Keyword)
    private Language language;

    @Field(type = FieldType.Integer)
    private Integer star;

    @Field(type = FieldType.Keyword)
    private String name;

    public Variable(String codeId, Language language, Integer star, String name) {
        if (Objects.isNull(codeId)) {
            throw new IllegalArgumentException("code id can't be null");
        }
        this.codeId = codeId;
        this.language = language;
        this.star = star;
        this.name = name;
    }

    public String id() {
        return id;
    }

    public String codeId() {
        return codeId;
    }

    public Language language() {
        return language;
    }

    public Integer star() {
        return star;
    }

    public String name() {
        return name;
    }
}
