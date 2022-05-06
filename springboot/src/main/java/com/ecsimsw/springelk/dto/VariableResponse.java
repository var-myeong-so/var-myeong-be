package com.ecsimsw.springelk.dto;

import com.ecsimsw.springelk.domain.Language;
import com.ecsimsw.springelk.domain.Variable;

import java.util.List;
import java.util.stream.Collectors;

public class VariableResponse {

    private String id;
    private String codeId;
    private Language language;
    private Integer star;
    private String name;

    public VariableResponse(String id, String codeId, Language language, Integer star, String name) {
        this.id = id;
        this.codeId = codeId;
        this.language = language;
        this.star = star;
        this.name = name;
    }

    public static VariableResponse of(Variable variable) {
        return new VariableResponse(
                variable.id(),
                variable.codeId(),
                variable.language(),
                variable.star(),
                variable.name()
        );
    }

    public static List<VariableResponse> listOf(List<Variable> variables) {
        return variables.stream()
                .map(VariableResponse::of)
                .collect(Collectors.toList());
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
}
