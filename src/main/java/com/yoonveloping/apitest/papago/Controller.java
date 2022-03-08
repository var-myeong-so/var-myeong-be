package com.yoonveloping.apitest.papago;

import static com.yoonveloping.apitest.papago.Secret.*;

import java.util.HashMap;
import java.util.Map;

public class Controller {

	private static final String REQUEST_HEADER_ID_KEY = "X-Naver-Client-Id";
	private static final String REQUEST_HEADER_SECRET_KEY = "X-Naver-Client-Secret";
	private final String sourceLanguage;

	public Controller(String sourceLanguage) {
		this.sourceLanguage = sourceLanguage;
	}

	public void control() {
		SourceData sourceData = new SourceData(sourceLanguage);
		Request request = new Request(sourceData.getSourceLanguage(), makeRequestHeaders());
		String responseBody = request.post();
		System.out.println(responseBody);
	}

	private Map<String, String> makeRequestHeaders() {
		final Map<String, String> requestHeaders = new HashMap<>();
		requestHeaders.put(REQUEST_HEADER_ID_KEY, CLIENT_ID);
		requestHeaders.put(REQUEST_HEADER_SECRET_KEY, CLIENT_SECRET);
		return requestHeaders;
	}
}
