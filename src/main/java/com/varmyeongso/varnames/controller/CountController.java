package com.varmyeongso.varnames.controller;

import com.varmyeongso.varnames.dto.CounterResponse;
import com.varmyeongso.varnames.service.CountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/count")
public class CountController {

	@Autowired
	private CountService countService;

	@GetMapping
	public CounterResponse countClassName(@RequestParam String target) {
		long numberOfCodesByVariableName = countService.countVariableName(target);
		long numberOfCodesByClassName = countService.countClassName(target);
		long totalNumber = numberOfCodesByVariableName + numberOfCodesByClassName;
		return new CounterResponse(
			totalNumber,
			numberOfCodesByVariableName,
			numberOfCodesByClassName
			);
	}

	@GetMapping("/all")
	public long countAll() {
		return countService.countAllCodes();
	}
}
