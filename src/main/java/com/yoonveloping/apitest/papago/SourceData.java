package com.yoonveloping.apitest.papago;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import org.springframework.stereotype.Component;

@Component
public class SourceData {

	private String sourceLanguage;

	public SourceData(String sourceLanguage) {
		this.sourceLanguage = sourceLanguage;
	}

	public SourceData() {
	}

	public String encode(String sourceLanguage) {
		return URLEncoder.encode(sourceLanguage, StandardCharsets.UTF_8);
	}

	public String getSourceLanguage() {
		return sourceLanguage;
	}
}
