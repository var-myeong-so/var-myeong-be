package com.varmyeongso.varnames.service;

import com.varmyeongso.varnames.domain.Code;
import com.varmyeongso.varnames.domain.CodeRepository;
import com.varmyeongso.varnames.util.CodeFactory;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodeService {

	private static final String KEYWORD_CLASS = "class";
	private static final String KEYWORD_VAR = "variable";

	@Autowired
	private final CodeRepository codeRepository;

	public CodeService(CodeRepository codeRepository) {
		this.codeRepository = codeRepository;
	}

	public List<Code> searchCodesByClassName(String keyword, String target) {
		if (keyword.equals(KEYWORD_CLASS)) {
			String baseName = CodeFactory.addExtension(target);
			return codeRepository.findCodesByClassName(baseName);
		}
		if (keyword.equals(KEYWORD_VAR)) {
//			return codeRepository.findCodesByVariableName(target);
		}
		return null;
	}

	public List<Code> searchCodesByAll(String target) {
//		List<Code> codesByVariableName = codeRepository.findCodesByVariableName(target);
		String baseName = CodeFactory.addExtension(target);
		List<Code> codes = codeRepository.findCodesByClassName(baseName);
//		codes.addAll(codesByVariableName);
		return codes;
	}
}
