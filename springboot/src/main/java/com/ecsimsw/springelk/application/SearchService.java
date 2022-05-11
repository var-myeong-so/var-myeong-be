package com.ecsimsw.springelk.application;

import com.ecsimsw.springelk.domain.Code;
import com.ecsimsw.springelk.domain.CodeRepository;
import com.ecsimsw.springelk.domain.VariableRepository;
import com.ecsimsw.springelk.dto.SearchResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@Service
public class SearchService {

	private final CodeRepository codeRepository;
	private final VariableRepository variableRepository;

	public SearchService(CodeRepository codeRepository, VariableRepository variableRepository) {
		this.codeRepository = codeRepository;
		this.variableRepository = variableRepository;
	}

	public List<SearchResponse> findCodeByClassName(String className, Pageable pageable) {
		return codeRepository.findAllByClassName(className, pageable).stream()
			.map(code -> SearchResponse.parsingWithNLines(code, code.classIndex(), 3))
			.collect(Collectors.toList());
	}

	public List<SearchResponse> findCodeByVariableName(String variableName, Pageable pageable) {
		return variableRepository.findAllByName(variableName, pageable).stream()
			.map(variable -> {
				Code code = codeRepository.findById(variable.codeId()).orElseThrow();
				return SearchResponse.parsingWithNLines(code, code.firstPositionOf(variable), 3);
			}).collect(Collectors.toList());
	}

	public Integer countCodeByClassName(String className) {
		return codeRepository.countByClassName(className);
	}

	public Integer countCodeByVariableName(String variableName) {
		return variableRepository.countByName(variableName);
	}
}
