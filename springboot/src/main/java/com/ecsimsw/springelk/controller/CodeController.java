package com.ecsimsw.springelk.controller;

import com.ecsimsw.springelk.application.CodeService;
import com.ecsimsw.springelk.domain.Code;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CodeController {

    private final CodeService codeService;

    public CodeController(CodeService codeService) {
        this.codeService = codeService;
    }

    // ex, localhost:8080/content?regex=String
    @GetMapping
    public List<Code> findAllByContent(@RequestParam String regex) {
        return codeService.findAllByContentRegex(regex);
    }
}
