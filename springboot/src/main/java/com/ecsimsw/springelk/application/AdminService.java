package com.ecsimsw.springelk.application;

import com.ecsimsw.springelk.domain.Code;
import com.ecsimsw.springelk.domain.CodeRepository;
import com.ecsimsw.springelk.domain.Variable;
import com.ecsimsw.springelk.domain.VariableRepository;
import com.ecsimsw.springelk.dto.CodeFile;
import com.ecsimsw.springelk.dto.CodeResponse;
import com.ecsimsw.springelk.dto.VariableResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AdminService {

    private final CodeRepository codeRepository;
    private final VariableRepository variableRepository;

    public AdminService(CodeRepository codeRepository, VariableRepository variableRepository) {
        this.codeRepository = codeRepository;
        this.variableRepository = variableRepository;
    }

    @Transactional
    public List<CodeResponse> storeCode(List<CodeFile> codeFiles) {
        return codeFiles.stream()
                .map(this::storeCode)
                .collect(Collectors.toList());
    }

    @Transactional
    public CodeResponse storeCode(CodeFile codeFile) {
        final Code code = codeRepository.save(codeFile.asCode());
        variableRepository.saveAll(code.variableNames());
        return CodeResponse.of(code);
    }

    @Transactional
    public VariableResponse storeVariableInCode(String codeId, String name) {
        final Code code = findCodeById(codeId);
        final Variable saved = variableRepository.save(new Variable(code.id(), code.language(), code.star(), name));
        return VariableResponse.of(saved);
    }

    @Transactional
    public void deleteCodeById(String id) {
        variableRepository.deleteAllByCodeId(id);
        codeRepository.deleteById(id);
    }

    @Transactional
    public void deleteVariableById(String id) {
        variableRepository.deleteById(id);
    }

    @Transactional
    public void deleteVariableByName(String name) {
        variableRepository.deleteAllByName(name);
    }

    @Transactional(readOnly = true)
    public List<CodeResponse> searchAllCode(Pageable pageable) {
        if (!Objects.isNull(pageable)) {
            final List<Code> codes = codeRepository.findAll(pageable).toList();
            return CodeResponse.listOf(codes);
        }
        return CodeResponse.listOf(codeRepository.findAll());
    }

    @Transactional
    public List<VariableResponse> searchAllVariable(Pageable pageable) {
        if (!Objects.isNull(pageable)) {
            final List<Variable> variables = variableRepository.findAll(pageable).toList();
            return VariableResponse.listOf(variables);
        }
        return VariableResponse.listOf(variableRepository.findAll());
    }

    private Code findCodeById(String codeId) {
        return codeRepository.findById(codeId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid code id"));
    }
}
