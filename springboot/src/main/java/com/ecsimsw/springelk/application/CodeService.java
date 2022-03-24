package com.ecsimsw.springelk.application;

import com.ecsimsw.springelk.domain.Code;
import com.ecsimsw.springelk.domain.CodeRepository;
import com.ecsimsw.springelk.domain.Language;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CodeService {

    private final CodeRepository codeRepository;

    public CodeService(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    @Transactional
    public Code create(Language language, String path, String className, String content) {
        final Code code = new Code(language, path, className, content);
        return codeRepository.save(code);
    }

    @Transactional
    public void deleteAll() {
        codeRepository.deleteAll();
    }

    @Transactional(readOnly = true)
    public List<Code> findAll() {
        List<Code> codes = new ArrayList<>();
        codeRepository.findAll().forEach(codes::add);
        return codes;
    }

    @Transactional(readOnly = true)
    public List<Code> findAllByContentRegex(String regex) {
        return codeRepository.findCodeByContentRegex(regex);
    }

    @Transactional(readOnly = true)
    public List<Code> findAllByContentKeyword(String keyword) {
        return codeRepository.findCodeByContentContaining(keyword);
    }
}
