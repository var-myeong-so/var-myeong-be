package com.ecsimsw.springelk.domain;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ClassNameRepository extends ElasticsearchRepository<ClassName, String> {
}
