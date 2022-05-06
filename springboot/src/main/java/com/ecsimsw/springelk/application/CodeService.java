package com.ecsimsw.springelk.application;

import com.ecsimsw.springelk.domain.Code;
import com.ecsimsw.springelk.domain.CodeRepository;
import com.ecsimsw.springelk.domain.VariableNameRepository;
import com.ecsimsw.springelk.dto.CodeFile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void create(CodeFile codeFile) {
        final Code code = codeRepository.save(codeFile.asCode());
        variableNameRepository.saveAll(code.variableNames());
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
