package com.ecsimsw.springelk.domain;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface VariableNameRepository extends ElasticsearchRepository<Variable, String> {
}
