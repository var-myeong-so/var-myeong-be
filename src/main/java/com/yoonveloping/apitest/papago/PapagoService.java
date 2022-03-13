package com.yoonveloping.apitest.papago;

import com.fasterxml.jackson.databind.JsonNode;

public interface PapagoService {

	String parsing(JsonNode jsonObject);

	JsonNode post(String originalText);
}
