package com.ecsimsw.springelk.domain;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface VariableRepository extends ElasticsearchRepository<Variable, String> {

    void deleteAllByCodeId(String codeId);

    void deleteAllByName(String name);

    @Override
    List<Variable> findAll();
}
