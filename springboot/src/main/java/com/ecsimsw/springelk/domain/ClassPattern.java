package com.ecsimsw.springelk.domain;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ClassPattern {

	JAVA(Language.JAVA, Pattern.compile("(?<=(class\\s))([a-zA-Z0-9_]*)(?=(\\s))|(?<=(enum\\s))([a-zA-Z0-9_]*)(?=(\\s))|(?<=(interface\\s))([a-zA-Z0-9_]*)(?=(\\s))"));

	private final Language language;
	private final Pattern pattern;

	ClassPattern(Language language, Pattern pattern) {
		this.language = language;
		this.pattern = pattern;
	}

	public static ClassPattern of(Language language) {
		return Arrays.stream(values())
			.filter(pattern -> pattern.language == language)
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("Invalid language"));
	}

	public Matcher matcher(String input) {
		return pattern.matcher(input);
	}

	public boolean matches(String input) {
		return pattern.matcher(input).find();
	}
}
