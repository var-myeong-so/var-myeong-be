package com.yoonveloping.apitest.papago;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

@Service
public class PapagoServiceImpl implements PapagoService {

	private static final String API_URL = "https://openapi.naver.com/v1/papago/n2mt";
	private static final String TRANSLATED_TEXT_IN_JSON = "translatedText";
	private final Request requestHeaders;

	public PapagoServiceImpl(Request requestHeaders) {
		this.requestHeaders = requestHeaders;
	}

	@Override
	public String parsing(JsonNode jsonObject) {
		final JsonNode translatedText = jsonObject.findValue(TRANSLATED_TEXT_IN_JSON);
		return translatedText.asText();
	}

	// TODO::WebClient로 리팩토링
	@Override
	public JsonNode post(String originalText) {
		HttpURLConnection con = connect();
		String postParams = "source=ko&target=en&text=" + originalText; //원본언어: 한국어 (ko) -> 목적언어: 영어 (en)
		try {
			con.setRequestMethod(String.valueOf(HttpMethod.POST));
			for (Map.Entry<String, String> header : requestHeaders.getRequestHeaders().entrySet()) {
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

	private JsonNode readBody(InputStream body) {
		ObjectMapper mapper = new ObjectMapper()
			.registerModule(new JavaTimeModule())
			.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
			.configure(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE, false);

		final InputStreamReader streamReader = new InputStreamReader(body);
		try (BufferedReader lineReader = new BufferedReader(streamReader)) {
			final StringBuilder responseBody = new StringBuilder();
			String line;
			while ((line = lineReader.readLine()) != null) {
				responseBody.append(line);
			}
			return mapper.readTree(responseBody.toString());
		} catch (IOException IOE) {
			throw new RuntimeException("API 응답을 읽는데 실패했습니다.", IOE);
		}
	}
}
