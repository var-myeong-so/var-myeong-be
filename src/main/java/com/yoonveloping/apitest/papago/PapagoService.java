package com.yoonveloping.apitest.papago;

import com.fasterxml.jackson.databind.JsonNode;
import reactor.core.publisher.Mono;

public interface PapagoService {

	String parsing(JsonNode jsonObject);

	Mono<String> post(String originalText);
}
