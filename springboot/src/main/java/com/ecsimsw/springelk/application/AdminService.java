package com.ecsimsw.springelk.application;

import com.ecsimsw.springelk.domain.Code;
import com.ecsimsw.springelk.domain.CodeRepository;
import com.ecsimsw.springelk.domain.Variable;
import com.ecsimsw.springelk.domain.VariableRepository;
import com.ecsimsw.springelk.dto.CodeFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class AdminService {

    private final CodeRepository codeRepository;
    private final VariableRepository variableRepository;

    public AdminService(CodeRepository codeRepository, VariableRepository variableRepository) {
        this.codeRepository = codeRepository;
        this.variableRepository = variableRepository;
    }

    @Transactional
    public void storeCode(CodeFile codeFile) {
        final Code code = codeRepository.save(codeFile.asCode());
        variableRepository.saveAll(code.variableNames());
    }

    @Transactional
    public void storeVariableInCode(String codeId, String name) {
        final Code code = findCodeById(codeId);
        variableRepository.save(new Variable(code.id(), code.language(), code.star(), name));
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
    public List<Code> searchAllCode(Pageable pageable) {
        if(!Objects.isNull(pageable)) {
            return codeRepository.findAll(pageable).toList();
        }
        return codeRepository.findAll();
    }

    @Transactional
    public List<Variable> searchAllVariable(Pageable pageable) {
        if(!Objects.isNull(pageable)) {
            return variableRepository.findAll(pageable).toList();
        }
        return variableRepository.findAll();
    }

    private Code findCodeById(String codeId) {
        return codeRepository.findById(codeId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid code id"));
    }
}
