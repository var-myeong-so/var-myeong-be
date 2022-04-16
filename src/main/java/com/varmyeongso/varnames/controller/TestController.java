package com.varmyeongso.varnames.controller;

import com.varmyeongso.varnames.domain.CodeRepository;
import com.varmyeongso.varnames.dto.CodeResponse;
import com.varmyeongso.varnames.dto.CodeResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private CodeRepository codeRepository;

    @GetMapping("/code/{keyword}")
    public CodeResponses search(
        @PathVariable String keyword,
        @RequestParam String target,
        @PageableDefault(size = 10, page = 0) Pageable pageable
    ) {
        final List<CodeResponse> codes = codeRepository.findAll(pageable)
            .stream()
            .map(CodeResponse::of)
            .collect(Collectors.toList());
        return CodeResponse.listOf(codes);
    }
}
