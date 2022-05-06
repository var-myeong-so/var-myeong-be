package com.ecsimsw.springelk.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface VariableRepository extends ElasticsearchRepository<Variable, String> {

    void deleteAllByCodeId(String codeId);

    void deleteAllByName(String name);

    List<Variable> findAllByName(String name, Pageable pageable);

    Integer countByName(String variableName);

    @Override
    List<Variable> findAll();
}
