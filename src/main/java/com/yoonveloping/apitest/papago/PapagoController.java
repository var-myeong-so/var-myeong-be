package com.yoonveloping.apitest.papago;

import com.yoonveloping.apitest.AppConfig;

public class PapagoController {

	public PapagoController() {
	}

	public void control() {
		PapagoService service = AppConfig.papagoService();
		String responseBody = service.post();
		System.out.println(responseBody);
		System.out.println(service.parsing(responseBody));
	}
}
