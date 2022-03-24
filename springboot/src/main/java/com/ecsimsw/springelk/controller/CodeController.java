package com.ecsimsw.springelk.controller;

import com.ecsimsw.springelk.application.CodeService;
import com.ecsimsw.springelk.domain.CodeRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CodeController {

    private final CodeService codeService;

    public CodeController(CodeService codeService) {
        this.codeService = codeService;
    }

    @GetMapping
    public String findAll(@RequestParam String regex) {
        return codeService.findByKeyword(regex).toString();
    }
}
