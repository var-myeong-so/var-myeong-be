package com.yoonveloping.apitest.papago;

import com.yoonveloping.apitest.AppConfig;

public class PapagoController {

	public PapagoController() {
	}

	public void control() {
		String originalText = "가져오다.";
		PapagoService service = AppConfig.papagoService();
		String responseBody = service.post(originalText);
		System.out.println(responseBody);
		System.out.println(service.parsing(responseBody));
	}
}
