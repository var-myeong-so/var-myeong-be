package com.ecsimsw.springelk.util;

import com.ecsimsw.springelk.domain.Language;
import com.ecsimsw.springelk.dto.CodeFile;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CodeFileFactory {

	private CodeFileFactory() { }

	public static CodeFile makeCodeFile(String code, String url) throws IOException {
		List<String> urlSplit = List.of(url.split("/"));
		String userName = urlSplit.get(3);
		String projectName = List.of(urlSplit.get(4).split("\\.")).get(0);
		return new CodeFile(
			calculateClassName(),
			userName,
			projectName,
			calculatePackageName(code),
			calculateStar(url),
			calculatePath(),
			Language.JAVA,
			code);
	}

	private static String calculatePath() {
		//TODO::path 구하는 법 고민 후 적용한다.
		return "path";
	}

	private static String calculatePackageName(String code) {
		List<String> codeSplit = List.of(code.split("\n"));
		for (String line : codeSplit) {
			if (line.contains("package")) {
				List<String> splitBySpace = List.of(line.split(" "));
				List<String> splitByDot = List.of(splitBySpace.get(1).split("\\."));
				String packageNameWithSemiColon = splitByDot.get(splitByDot.size() - 1);
				return packageNameWithSemiColon.replace(";", "");
			}
		}
		return "";
	}

	private static Integer calculateStar(String urlPath) throws IOException {
		String pageContents;
		StringBuilder contents = new StringBuilder();

		URL url = new URL(urlPath);
		URLConnection con = url.openConnection();
		InputStreamReader reader = new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8);
		BufferedReader buff = new BufferedReader(reader);
		while ((pageContents = buff.readLine()) != null) {
			contents.append(pageContents);
			contents.append("\r\n");
		}
		buff.close();
		return parseStar(contents.toString());
	}

	public static int parseStar(String contents) {
		List<String> splitByNewLine = List.of(contents.split("\n"));
		for (String line : splitByNewLine) {
			line = line.trim();
			if (line.contains("repo-stars-counter-star")) {
				List<String> splitByEqual = List.of(line.split("="));
				String parsedString = splitByEqual.get(2);
				List<String> listContainStarCount = List.of(parsedString.replace("\"", "").split(" "));
				return Integer.parseInt(listContainStarCount.get(0));
			}
		}
		return 0;
	}

	private static String calculateClassName() {
		// TODO::regex로 수정이 필요하다.
		return "className";
	}

}
