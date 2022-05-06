package com.ecsimsw.springelk.controller;

import com.ecsimsw.springelk.application.CodeService;
import com.ecsimsw.springelk.dto.CodeResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final CodeService codeService;

    public UserController(CodeService codeService) {
        this.codeService = codeService;
    }

    @GetMapping("/code/class/{name}")
    public ResponseEntity<List<CodeResponse>> searchCodeByName(@PathVariable String name,
                                                               Pageable pageable) {
        return ResponseEntity.ok(codeService.findCodeByClassName(name, pageable));
    }

    @GetMapping("/code/variable/{name}")
    public ResponseEntity<List<CodeResponse>> searchVariableByName(@PathVariable String name,
                                                                   Pageable pageable) {
        return ResponseEntity.ok(codeService.findCodeByVariableName(name, pageable));
    }

    @GetMapping("/code/count/class/{name}")
    public ResponseEntity<Integer> countByClassName(@PathVariable String name) {
        return ResponseEntity.ok(codeService.countCodeByClassName(name));
    }

    @GetMapping("/code/count/variable/{name}")
    public ResponseEntity<Integer> countByVariableName(@PathVariable String name) {
        return ResponseEntity.ok(codeService.countCodeByVariableName(name));
    }
}
