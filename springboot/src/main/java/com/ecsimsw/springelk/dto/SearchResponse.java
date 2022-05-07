package com.ecsimsw.springelk.dto;

import com.ecsimsw.springelk.domain.Code;
import com.ecsimsw.springelk.domain.Language;

import java.util.ArrayList;
import java.util.List;

public class SearchResponse {

    private String id;
    private Language language;
    private String path;
    private Integer star;
    private String className;
    private String codeLines;

    public SearchResponse(String id, Language language, String path, Integer star, String className, String codeLines) {
        this.id = id;
        this.language = language;
        this.path = path;
        this.star = star;
        this.className = className;
        this.codeLines = codeLines;
    }

    public static List<SearchResponse> listOf(List<Code> codes) {
        final List<SearchResponse> searchResponses = new ArrayList<>();
        for (Code code : codes) {
            final String codeLines = code.content();
            searchResponses.add(new SearchResponse(code.id(), code.language(), code.path(), code.star(), code.className(), codeLines));
        }
        return searchResponses;
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

    public Integer getStar() {
        return star;
    }

    public String getClassName() {
        return className;
    }

    public String getCodeLines() {
        return codeLines;
    }
}
