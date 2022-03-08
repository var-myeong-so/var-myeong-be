package com.yoonveloping.apitest.papago;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class Request {

	private static final String API_URL = "https://openapi.naver.com/v1/papago/n2mt";
	private final String sourceLanguage;
	private final Map<String, String> requestHeaders;

	public Request(String sourceLanguage, Map<String, String> requestHeaders) {
		this.sourceLanguage = sourceLanguage;
		this.requestHeaders = requestHeaders;
	}

	public String post() {
		HttpURLConnection con = connect();
		String postParams = "source=ko&target=en&text=" + sourceLanguage; //원본언어: 한국어 (ko) -> 목적언어: 영어 (en)
		try {
			con.setRequestMethod("POST");
			for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
				con.setRequestProperty(header.getKey(), header.getValue());
			}
			con.setDoOutput(true);
			try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
				wr.write(postParams.getBytes());
				wr.flush();
			}
			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 응답
				return readBody(con.getInputStream());
			}
			return readBody(con.getErrorStream());
		} catch (IOException IOE) {
			throw new RuntimeException("API 요청과 응답 실패", IOE);
		} finally {
			con.disconnect();
		}
	}

	private HttpURLConnection connect() {
		try {
			URL url = new URL(API_URL);
			return (HttpURLConnection) url.openConnection();
		} catch (MalformedURLException MUE) {
			throw new RuntimeException("API URL이 잘못되었습니다. : " + API_URL, MUE);
		} catch (IOException IOE) {
			throw new RuntimeException("연결이 실패했습니다. : " + API_URL, IOE);
		}
	}

	private String readBody(InputStream body) {
		InputStreamReader streamReader = new InputStreamReader(body);
		try (BufferedReader lineReader = new BufferedReader(streamReader)) {
			StringBuilder responseBody = new StringBuilder();
			String line;
			while ((line = lineReader.readLine()) != null) {
				responseBody.append(line);
			}
			return responseBody.toString();
		} catch (IOException IOE) {
			throw new RuntimeException("API 응답을 읽는데 실패했습니다.", IOE);
		}
	}
}
