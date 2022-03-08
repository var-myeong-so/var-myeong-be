package com.yoonveloping.apitest.papago;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class SourceData {

	private final String sourceLanguage;

	public SourceData(String sourceLanguage) {
		this.sourceLanguage = encode(sourceLanguage);
	}

	private String encode(String sourceLanguage) {
		return URLEncoder.encode(sourceLanguage, StandardCharsets.UTF_8);
	}

	public String getSourceLanguage() {
		return sourceLanguage;
	}
}
