package com.yoonveloping.apitest;

import com.yoonveloping.apitest.papago.PapagoController;

// 네이버 기계번역 (Papago SMT) API 예제
public class ApiTestApplication {

	public static void main(String[] args) {
		PapagoController papagoController = new PapagoController();
		papagoController.control();
	}
}
