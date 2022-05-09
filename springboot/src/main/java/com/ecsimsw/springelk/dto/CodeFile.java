package com.ecsimsw.springelk.dto;

import com.ecsimsw.springelk.domain.Code;
import com.ecsimsw.springelk.domain.Language;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CodeFile {

	private final String className;
	private final String userName;
	private final String projectName;
	private final String packageName;
	private final Integer star;
	private final String path;
	private final Language language;
	private final String content;

	public CodeFile(String className, String userName, String projectName,
		String packageName, Integer star,
		String path,
		Language language, String content) {
		this.className = className;
		this.userName = userName;
		this.projectName = projectName;
		this.packageName = packageName;
		this.star = star;
		this.path = path;
		this.language = language;
		this.content = content;
	}

	public static CodeFile of(String fileContents, String url) throws IOException {
		List<String> urlSplit = List.of(url.split("/"));
		String userName = urlSplit.get(3);
		String projectName = List.of(urlSplit.get(4).split("\\.")).get(0);
		return new CodeFile(
			calculateClassName(fileContents),
			userName,
			projectName,
			calculatePackageName(fileContents),
			calculateStar(url),
			calculatePath(url),
			Language.JAVA,
			fileContents);
	}

	private static String calculatePath(String url) {
		//TODO::path 구하는 법 고민 후 적용한다.
		return url;
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
		InputStreamReader reader = new InputStreamReader(con.getInputStream(),
			StandardCharsets.UTF_8);
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
				List<String> listContainStarCount = List.of(
					parsedString.replace("\"", "").split(" "));
				return Integer.parseInt(listContainStarCount.get(0));
			}
		}
		return 0;
	}

	private static String calculateClassName(String fileName) {
		// TODO::regex로 수정이 필요하다.
		return "className";
	}

	public Code asCode() {
		return new Code(language, path, star, className, content);
	}

	public String getClassName() {
		return className;
	}

	public Integer getStar() {
		return star;
	}

	public String getPath() {
		return path;
	}

	public Language getLanguage() {
		return language;
	}

	public String getContent() {
		return content;
	}

	public String getUserName() {
		return userName;
	}

	public String getProjectName() {
		return projectName;
	}

	public String getPackageName() {
		return packageName;
	}
}
