package com.yoonveloping.apitest.webclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yoonveloping.apitest.sample.SampleController;
import java.time.Duration;
import java.util.Objects;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@RestController
public class WebclientController {

    private static final String TRANSLATED_TEXT_IN_JSON = "translatedText";
    private static final String PAPAGO_API_URI = "https://openapi.naver.com/v1/papago/n2mt";
    private static final String HEADER_ID_KEY = "X-Naver-Client-Id";
    private static final String HEADER_SECRET_KEY = "X-Naver-Client-Secret";

    @GetMapping("/test")
    public Mono<String> doTest(@RequestParam(required = false) String externalUrl) {
        if (Objects.isNull(externalUrl)) {
            externalUrl = SampleController.SAMPLE_API_PATH;
        }
        return webclientWithTimeOut(3)
                .get()
                .uri(externalUrl)
                .retrieve()
                .bodyToMono(String.class);
    }

	private WebClient webclientWithTimeOut(long seconds) {
		final HttpClient client = HttpClient.create()
				.responseTimeout(Duration.ofSeconds(seconds));
		return WebClient.builder()
				.clientConnector(new ReactorClientHttpConnector(client))
				.build();
	}

	public String parsing(JsonNode jsonObject) {
		final JsonNode translatedText = jsonObject.findValue(TRANSLATED_TEXT_IN_JSON);
		return translatedText.asText();
	}

	@GetMapping("/translate")
	public String doTranslate(@RequestParam(required = false) String originalText) {
		WebClient webClient = WebClient.builder()
			.defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json")
			.build();

		System.out.println("originalText = " + originalText);

		MultiValueMap<String, String> params = getParams(originalText);
		Mono<JsonNode> jsonMono = getJsonMono(webClient, params);
		JsonNode jsonData = jsonMono.block();
		assert jsonData != null;
		String parsedData = parsing(jsonData);
		System.out.println("parsedData = " + parsedData);
		return parsedData;
	}

	private Mono<JsonNode> getJsonMono(WebClient webClient, MultiValueMap<String, String> params) {
		Mono<String> stringMono = webClient.post()
			.uri(PAPAGO_API_URI)
			.headers(httpHeaders -> {
				httpHeaders.add(HEADER_ID_KEY, Secret.CLIENT_ID);
				httpHeaders.add(HEADER_SECRET_KEY, Secret.CLIENT_SECRET);
			})
			.body(BodyInserters.fromFormData(params))
			.retrieve()
			.bodyToMono(String.class);

		return stringMono.map(s -> {
			ObjectMapper mapper = new ObjectMapper();
			try {
				return mapper.readTree(s);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return null;
		});
	}

	private MultiValueMap<String, String> getParams(String originalText) {
		final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("source", "ko");
		params.add("target", "en");
		params.add("text", originalText);
		return params;
	}
}
