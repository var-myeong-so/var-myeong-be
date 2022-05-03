package com.varmyeongso.varnames.domain;

import java.awt.print.Pageable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeRepository extends JpaRepository<Code, Long> {

	long count();

	long countByClassName(String className);

	List<Code> findCodesByClassName(String className);

	List<Code> findAll(Pageable pageable);

//	List<Code> findCodesByVariableName(String variableName);
}
