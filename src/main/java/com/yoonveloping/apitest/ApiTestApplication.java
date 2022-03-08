package com.yoonveloping.apitest;

import com.yoonveloping.apitest.papago.Controller;

// 네이버 기계번역 (Papago SMT) API 예제
public class ApiTestApplication {

	private static final String SOURCE_LANGUAGE = "가져오다";

	public static void main(String[] args) {
		Controller controller = new Controller(SOURCE_LANGUAGE);
		controller.control();
	}
}
