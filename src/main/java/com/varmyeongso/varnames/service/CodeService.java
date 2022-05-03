package com.varmyeongso.varnames.service;

import com.varmyeongso.varnames.domain.Code;
import com.varmyeongso.varnames.domain.CodeRegex;
import com.varmyeongso.varnames.domain.CodeRepository;
import com.varmyeongso.varnames.util.CodeFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
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
			List<Code> codesByClassName = codeRepository.findCodesByClassName(baseName);
			return parse(codesByClassName);
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
		return parse(codes);
	}

	public void saveCodes(List<Code> codes) {
		codes.forEach(codeRepository::save);
	}

	private List<Code> parse(List<Code> code) {
		code.forEach(this::parseCode);
		return code;
	}

	private void parseCode(Code code) {
		String codeContext = code.getCode();
		List<String> codesByLine = List.of(codeContext.split("\n"));
		saveParsedCode(codesByLine, code);
	}

	private void saveParsedCode(List<String> codesByLine, Code code) {
		List<String> parsedCodes = new ArrayList<>();
		int targetIndex = calculateClassIndex(codesByLine);
		if (targetIndex < 5) {
			for (int i = 0; i < 10; i++) {
				parsedCodes.add(codesByLine.get(i));
			}
			code.setCode(makeString(parsedCodes));
			return;
		}
		for (int i = targetIndex - 5; i < targetIndex + 5; i++) {
			parsedCodes.add(codesByLine.get(i));
		}
		code.setCode(makeString(parsedCodes));
	}

	private int calculateClassIndex(List<String> codesByLine) {
		for (String line : codesByLine) {
			if (Pattern.matches(CodeRegex.CLASS_LINE_REGEX.getRegex(), line)) {
				return codesByLine.indexOf(line);
			}
		}
		return 0;
	}

	private String makeString(List<String> strings) {
		StringBuilder a = new StringBuilder();
		for (String string : strings) {
			a.append(string).append("\n");
		}
		return String.valueOf(a);
	}
}
