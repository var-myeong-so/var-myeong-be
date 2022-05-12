package com.ecsimsw.springelk.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface CodeRepository extends ElasticsearchRepository<Code, String> {

    List<Code> findAllByContentContaining(String word, Pageable pageable);

    List<Code> findAllByClassName(String className, Pageable pageable);

    Integer countByClassName(String className);

    @Override
    List<Code> findAll();
}
