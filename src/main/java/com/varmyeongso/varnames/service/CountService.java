package com.varmyeongso.varnames.service;

import com.varmyeongso.varnames.domain.CodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountService {

	private static final String JAVA_EXTENSION = ".java";

	@Autowired
	private CodeRepository codeRepository;

	public long countVariableName(String variableName) {
		return 0;
	}

	public long countClassName(String className) {
		String baseName = addExtension(className);
		return codeRepository.countByClassName(baseName);
	}

	public long countAllCodes() {
		return codeRepository.count();
	}

	private String addExtension(String className) {
		return className + JAVA_EXTENSION;
	}
}
