package com.yoonveloping.apitest.papago;

import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class Request {

	private final Map<String, String> requestHeaders;

	public Request(Map<String, String> requestHeaders) {
		this.requestHeaders = requestHeaders;
	}

	public Map<String, String> getRequestHeaders() {
		return requestHeaders;
	}
}
