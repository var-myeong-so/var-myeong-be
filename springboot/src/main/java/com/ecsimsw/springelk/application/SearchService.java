package com.ecsimsw.springelk.application;

import com.ecsimsw.springelk.domain.Code;
import com.ecsimsw.springelk.domain.CodeRepository;
import com.ecsimsw.springelk.domain.Variable;
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
        final List<Code> codes = codeRepository.findAllByClassName(className, pageable);
        // TODO :: 위,아래 3줄씩 뽑아 반환
        return SearchResponse.listOf(codes);
    }

    public List<SearchResponse> findCodeByVariableName(String variableName, Pageable pageable) {
        final List<Variable> variables = variableRepository.findAllByName(variableName, pageable);
        final List<Code> codes = variables.stream()
                .map(variable -> codeRepository.findById(variable.codeId()).orElseThrow())
                .collect(Collectors.toList());
        // TODO :: 위,아래 3줄씩 뽑아 반환
        return SearchResponse.listOf(codes);
    }

    public Integer countCodeByClassName(String className) {
        return codeRepository.countByClassName(className);
    }

    public Integer countCodeByVariableName(String variableName) {
        return variableRepository.countByName(variableName);
    }
}
