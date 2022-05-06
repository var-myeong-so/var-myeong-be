package com.ecsimsw.springelk.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "variable_name")
public class VariableName {

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

    public VariableName(String codeId, Language language, Integer star, String name) {
        this.codeId = codeId;
        this.language = language;
        this.star = star;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getCodeId() {
        return codeId;
    }

    public Language getLanguage() {
        return language;
    }

    public Integer getStar() {
        return star;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCodeId(String codeId) {
        this.codeId = codeId;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public void setName(String name) {
        this.name = name;
    }
}
