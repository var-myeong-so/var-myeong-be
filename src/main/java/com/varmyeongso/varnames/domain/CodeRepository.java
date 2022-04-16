package com.varmyeongso.varnames.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeRepository extends JpaRepository<Code, Long> {

	long count();

	long countByClassName(String className);

	List<Code> findCodesByClassName(String className);

//	List<Code> findCodesByVariableName(String variableName);
}
