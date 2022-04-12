package com.varmyeongso.varnames.dto;

import java.util.List;

public class CodeResponses {

    private final List<CodeResponse> codeResponses;

    public CodeResponses(List<CodeResponse> codeResponses) {
        this.codeResponses = codeResponses;
    }

    public List<CodeResponse> getCodeResponses() {
        return codeResponses;
    }
}
