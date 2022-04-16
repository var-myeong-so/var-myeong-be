package com.varmyeongso.varnames.controller;

import com.varmyeongso.varnames.dto.CodeResponse;
import com.varmyeongso.varnames.dto.CodeResponses;
import com.varmyeongso.varnames.service.CodeService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/code")
public class CodeController {

	private final CodeService codeService;

	public CodeController(CodeService codeService) {
		this.codeService = codeService;
	}

	@GetMapping("/{keyword}")
	public CodeResponses search(
		@PathVariable String keyword,
		@RequestParam String target,
		@PageableDefault(size = 10, page = 0) Pageable pageable
	) {
		List<CodeResponse> codes = codeService.searchCodesByClassName(keyword, target)
			.stream()
			.map(CodeResponse::of)
			.collect(Collectors.toList());
		return CodeResponse.listOf(codes);
	}

	@GetMapping
	public CodeResponses searchWithoutKeyword(
		@RequestParam String target,
		@PageableDefault(size = 10, page = 0) Pageable pageable
	) {
		final List<CodeResponse> codes = codeService.searchCodesByAll(target)
			.stream()
			.map(CodeResponse::of)
			.collect(Collectors.toList());
		return CodeResponse.listOf(codes);
	}
}
