package com.ecsimsw.springelk.dto;

import com.ecsimsw.springelk.domain.Code;
import com.ecsimsw.springelk.domain.Language;

import java.util.ArrayList;
import java.util.List;

public class SearchResponse {

	private String id;
	private Language language;
	private String path;
	private Integer star;
	private String className;
	private String codeLines;

	public SearchResponse(String id, Language language, String path, Integer star, String className,
		String codeLines) {
		this.id = id;
		this.language = language;
		this.path = path;
		this.star = star;
		this.className = className;
		this.codeLines = codeLines;
	}

	public static List<SearchResponse> listOf(List<Code> codes) {
		final List<SearchResponse> searchResponses = new ArrayList<>();
		for (Code code : codes) {
			final String codeLines = code.content();
			searchResponses.add(
				new SearchResponse(code.id(), code.language(), code.path(), code.star(), code.className(), codeLines));
		}
		return searchResponses;
	}

	public static SearchResponse of(Code code) {
		return new SearchResponse(
			code.id(),
			code.language(),
			code.path(),
			code.star(),
			code.className(),
			code.content()
		);
	}

	public static SearchResponse parsingWithNLines(Code code, int index, int n) {
		List<String> codeLines = code.contentLines();
		if (codeLines.size() < 2 * n + 1) {
			return of(code);
		}
		StringBuilder parsedCode = new StringBuilder();
		if (index < n) {
			for (int i = 0; i <= n; i++) {
				parsedCode.append(codeLines.get(i));
				parsedCode.append("\n");
			}
			return new SearchResponse(code.id(), code.language(), code.path(), code.star(), code.className(), parsedCode.toString());
		}
		for (int i = index - n; i <= index + n; i++) {
			parsedCode.append(codeLines.get(i));
			parsedCode.append("\n");
		}
		return new SearchResponse(
			code.id(),
			code.language(),
			code.path(),
			code.star(),
			code.className(),
			parsedCode.toString()
		);
	}

	public String getId() {
		return id;
	}

	public Language getLanguage() {
		return language;
	}

	public String getPath() {
		return path;
	}

	public Integer getStar() {
		return star;
	}

	public String getClassName() {
		return className;
	}

	public String getCodeLines() {
		return codeLines;
	}
}
