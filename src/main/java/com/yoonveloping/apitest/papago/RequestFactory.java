package com.yoonveloping.apitest.papago;

import java.util.HashMap;
import java.util.Map;

public class RequestFactory {

	private final String clientId;
	private final String clientSecret;

	private static final String REQUEST_HEADER_ID_KEY = "X-Naver-Client-Id";
	private static final String REQUEST_HEADER_SECRET_KEY = "X-Naver-Client-Secret";

	public RequestFactory(String clientId, String clientSecret) {
		this.clientId = clientId;
		this.clientSecret = clientSecret;
	}

	public Map<String, String> makeRequestHeaders() {
		final Map<String, String> requestHeaders = new HashMap<>();
		requestHeaders.put(REQUEST_HEADER_ID_KEY, clientId);
		requestHeaders.put(REQUEST_HEADER_SECRET_KEY, clientSecret);
		return requestHeaders;
	}
}
