package com.ecsimsw.springelk.application;

import com.ecsimsw.springelk.domain.*;
import com.ecsimsw.springelk.dto.CodeFile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CodeService {

	private final CodeRepository codeRepository;
	private final VariableNameRepository variableNameRepository;

	public CodeService(CodeRepository codeRepository, VariableNameRepository variableNameRepository) {
		this.codeRepository = codeRepository;
		this.variableNameRepository = variableNameRepository;
	}

	@Transactional
	public Code create(Language language, String path, Integer star, String className, String content) {
		final Code code = new Code(language, path, star, className, content);
		return codeRepository.save(code);
	}

	@Transactional
	public void create(CodeFile codeFile) {
		final Code code = codeRepository.save(codeFile.asCode());
		final List<VariableName> variableNames = code.variableNames();
		variableNameRepository.saveAll(variableNames);
	}

	@Transactional(readOnly = true)
	public List<Code> findAllByContentRegex(String regex) {
		return codeRepository.findCodeByContentRegex(regex);
	}

	@Transactional
	public long countByClassName(String className) {
		return codeRepository.countByClassName(className);
	}

	@Transactional
	public long countAll() {
		return codeRepository.count();
	}
}
