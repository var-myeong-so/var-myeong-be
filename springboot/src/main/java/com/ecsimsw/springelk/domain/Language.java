package com.ecsimsw.springelk.domain;

import java.util.regex.Pattern;

public enum Language {

	JAVA(Pattern.compile("(?<=([a-zA-Z0-9_][\\s]))([a-zA-Z0-9_]*)(?=(\\s=))"));

	private final Pattern variablePattern;

	Language(Pattern variablePattern) {
		this.variablePattern = variablePattern;
	}

	public Pattern variablePattern() {
		return variablePattern;
	}
}
