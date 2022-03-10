package com.yoonveloping.apitest.papago;

public interface PapagoService {

	String parsing(String jsonString);

	String post(String originalText);
}
