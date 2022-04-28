package com.varmyeongso.varnames.domain;

public enum CodeRegex {
	CLASS_LINE_REGEX("^[a-zA-Z0-9]+\\s+[class | interface | enum]+\\s+[a-zA-Z0-9_]+\\s+\\{+$"),
	CLASS_NAME_REGEX("\\w+");

	private final String regex;

	CodeRegex(String regex) {
		this.regex = regex;
	}

	public String getRegex() {
		return regex;
	}
}
