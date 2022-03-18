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
		Mono<String> stringMono = webClient.get()
			.uri("https://07217c92-4b0c-45fc-9454-e34a17088a2f.mock.pstmn.io/hello")
			.retrieve()
			.bodyToMono(String.class);
		return stringMono;
	}

}
