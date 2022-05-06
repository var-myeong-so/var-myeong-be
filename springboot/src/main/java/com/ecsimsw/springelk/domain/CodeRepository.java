package com.ecsimsw.springelk.domain;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface CodeRepository extends ElasticsearchRepository<Code, String> {

	@Override
	List<Code> findAll();
}
