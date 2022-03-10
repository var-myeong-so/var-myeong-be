package com.yoonveloping.apitest;

import com.yoonveloping.apitest.papago.PapagoService;
import com.yoonveloping.apitest.papago.PapagoServiceImpl;
import com.yoonveloping.apitest.papago.Request;
import com.yoonveloping.apitest.papago.RequestFactory;
import com.yoonveloping.apitest.papago.Secret;
import com.yoonveloping.apitest.papago.SourceData;

public class AppConfig {

	public static PapagoService papagoService() {
		RequestFactory requestFactory = new RequestFactory(Secret.CLIENT_ID, Secret.CLIENT_SECRET);
		String sourceLanguage = "가져오다";
		SourceData sourceData = new SourceData(sourceLanguage);
		Request request = new Request(requestFactory.makeRequestHeaders());
		return new PapagoServiceImpl(sourceData, request);
	}
}
