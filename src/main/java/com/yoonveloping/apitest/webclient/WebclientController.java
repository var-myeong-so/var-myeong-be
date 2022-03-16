package com.yoonveloping.apitest.webclient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class WebclientController {

	@GetMapping("/test")
	public Mono<String> doTest() {
		WebClient webClient = WebClient.create();
		return webClient.get()
			.uri("http://localhost:8080/webclient/test-create")
			.retrieve()
			.bodyToMono(String.class);
	}

}
