package com.ecsimsw.springelk.domain;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CodeRepository extends ElasticsearchRepository<Code, String> {
}

