package com.ecsimsw.springelk.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

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

    @Override
    public String toString() {
        return "Code{\n" +
                "id='" + id + "\n" +
                ", language=" + language + "\n" +
                ", path='" + path + "\n" +
                ", className='" + className + "\n" +
                ", content='" + content + "\n" +
                "}\n";
    }
}
