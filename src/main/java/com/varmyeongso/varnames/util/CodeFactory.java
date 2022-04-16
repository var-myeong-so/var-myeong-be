package com.varmyeongso.varnames.util;

public class CodeFactory {

	private static final String JAVA_EXTENSION = ".java";

	private CodeFactory() {}

	public static String addExtension(String className) {
		return className + JAVA_EXTENSION;
	}
}
