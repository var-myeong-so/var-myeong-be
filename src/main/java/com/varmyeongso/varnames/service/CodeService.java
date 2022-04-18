package com.varmyeongso.varnames.service;

import com.varmyeongso.varnames.domain.Code;
import com.varmyeongso.varnames.domain.CodeRepository;
import com.varmyeongso.varnames.util.CodeFactory;
import java.util.ArrayList;
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

	private List<Code> parse(List<Code> codes, String target) {
		codes.forEach(code -> parseCode(target, code));
		return codes;
	}

	private void parseCode(String target, Code code) {
		String codeContext = code.getCode();
		List<String> codesSplitWithNewLine = List.of(codeContext.split("\n"));
		for (String sentence : codesSplitWithNewLine) {
			if (parseWithIndex(target, code, codesSplitWithNewLine, sentence)) {
				break;
			}
		}
	}

	private boolean parseWithIndex(String target, Code code, List<String> split, String sentence) {
		// 여기서도 결국 class에 대한 regex가 필요하겠네요
		List<String> parsedCodes = new ArrayList<>();
		int index;
		if (sentence.contains(target)) {
			index = split.indexOf(sentence);
			if (index < 5) {
				for (int i = 0; i < 10; i++) {
					parsedCodes.add(split.get(i));
				}
				code.setCode(makeString(parsedCodes));
				return true;
			}
			for (int i = index - 5; i < index + 5; i++) {
				parsedCodes.add(split.get(i));
			}
			code.setCode(makeString(parsedCodes));
			return true;
		}
		return false;
	}

	private String makeString(List<String> strings) {
		StringBuilder a = new StringBuilder();
		for (String string : strings) {
			a.append(string).append("\n");
		}
		return String.valueOf(a);
	}
}
