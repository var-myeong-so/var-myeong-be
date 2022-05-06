package com.ecsimsw.springelk.controller;

import com.ecsimsw.springelk.application.AdminService;
import com.ecsimsw.springelk.domain.Code;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CodeController {

	private final AdminService adminService;

	public CodeController(AdminService adminService) {
		this.adminService = adminService;
	}
}
