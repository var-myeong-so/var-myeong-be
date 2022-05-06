package com.ecsimsw.springelk.controller;

import com.ecsimsw.springelk.application.AdminService;
import com.ecsimsw.springelk.application.CodeCrawler;
import com.ecsimsw.springelk.domain.Code;
import com.ecsimsw.springelk.domain.Variable;
import com.ecsimsw.springelk.dto.CodeFile;
import com.ecsimsw.springelk.dto.CodeResponse;
import com.ecsimsw.springelk.dto.VariableResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {

    private final CodeCrawler codeCrawler;
    private final AdminService adminService;

    public AdminController(CodeCrawler codeCrawler, AdminService adminService) {
        this.codeCrawler = codeCrawler;
        this.adminService = adminService;
    }

    @PutMapping("/admin/code")
    public ResponseEntity<List<CodeResponse>> saveProject(String url) {
        final List<CodeFile> codeFiles = codeCrawler.execute(url);
        return ResponseEntity.ok(adminService.storeCode(codeFiles));
    }

    @PutMapping("/admin/code/{codeId}/variable/{variableName}")
    public ResponseEntity<VariableResponse> saveVariable(@PathVariable String codeId,
                                                         @PathVariable String variableName) {
        return ResponseEntity.ok(adminService.storeVariableInCode(codeId, variableName));
    }

    @DeleteMapping("/admin/code/{id}")
    public ResponseEntity<Void> deleteCode(@PathVariable String id) {
        adminService.deleteCodeById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/admin/variable/{id}")
    public ResponseEntity<Void> deleteVariable(@PathVariable String id) {
        adminService.deleteVariableById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/admin/variable")
    public ResponseEntity<Void> deleteVariableByName(String name) {
        adminService.deleteVariableByName(name);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/admin/code")
    public ResponseEntity<List<CodeResponse>> searchAllCode(Pageable pageable) {
        final List<CodeResponse> codes = adminService.searchAllCode(pageable);
        return ResponseEntity.ok(codes);
    }

    @GetMapping("/admin/variable")
    public ResponseEntity<List<VariableResponse>> searchAllVariable(Pageable pageable) {
        final List<VariableResponse> variables = adminService.searchAllVariable(pageable);
        return ResponseEntity.ok(variables);
    }
}
