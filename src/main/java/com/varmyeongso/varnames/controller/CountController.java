package com.varmyeongso.varnames.controller;

import com.varmyeongso.varnames.domain.CodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/count")
public class CountController {

	private static final String JAVA_EXTENSION = ".java";

	@Autowired
	private CodeRepository codeRepository;

	@GetMapping
	public long countClassName(@RequestParam String className) {
		String baseName = addExtension(className);
		return codeRepository.countByClassName(baseName);
	}

	@GetMapping("/all")
	public long countAll() {
		return codeRepository.count();
	}

	private String addExtension(String className) {
		return className + JAVA_EXTENSION;
	}
}
