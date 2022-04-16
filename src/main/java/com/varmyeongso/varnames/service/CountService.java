package com.varmyeongso.varnames.service;

import com.varmyeongso.varnames.domain.CodeRepository;
import com.varmyeongso.varnames.util.CodeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountService {

	@Autowired
	private final CodeRepository codeRepository;

	public CountService(CodeRepository codeRepository) {
		this.codeRepository = codeRepository;
	}

	public long countVariableName(String variableName) {
		return 0;
	}

	public long countClassName(String className) {
		String baseName = CodeFactory.addExtension(className);
		return codeRepository.countByClassName(baseName);
	}

	public long countAllCodes() {
		return codeRepository.count();
	}
}
