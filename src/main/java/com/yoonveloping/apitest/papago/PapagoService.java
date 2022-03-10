package com.yoonveloping.apitest.papago;

public interface PapagoService {

	String getSourceLanguage();

	String parsing(String jsonString);

	String post();
}
