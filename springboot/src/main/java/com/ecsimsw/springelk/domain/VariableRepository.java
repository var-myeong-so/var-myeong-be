package com.ecsimsw.springelk.domain;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface VariableRepository extends ElasticsearchRepository<Code, String> {
}
