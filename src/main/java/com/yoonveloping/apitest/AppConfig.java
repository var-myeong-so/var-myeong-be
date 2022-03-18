package com.yoonveloping.apitest;

import com.yoonveloping.apitest.papago.PapagoService;
import com.yoonveloping.apitest.papago.PapagoServiceImpl;
import com.yoonveloping.apitest.papago.Request;
import com.yoonveloping.apitest.papago.RequestFactory;
import com.yoonveloping.apitest.papago.Secret;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {

	@Bean
	public static PapagoService papagoService() {
		RequestFactory requestFactory = new RequestFactory(Secret.CLIENT_ID, Secret.CLIENT_SECRET);
		Request request = new Request(requestFactory.makeRequestHeaders());
		return new PapagoServiceImpl(request);
	}

//	@Bean
//	public WebClient webClient() {
//		return WebClient.builder()
//			.baseUrl("http://localhost:8080")
//			.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//			.build();
//	}
}
