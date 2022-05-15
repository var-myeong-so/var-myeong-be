package com.ecsimsw.springelk.application;

import com.ecsimsw.springelk.dto.CodeFile;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodeCrawler {

	private static final String CLONE_PATH = "springboot/src/main/resources/githubcodes";
	private static final String JAVA_EXTENSION = "java";
	private static final String NEW_LINE = "\n";
	private final List<File> javaFiles = new ArrayList<>();

	public List<CodeFile> execute(String url) throws IOException, InterruptedException {
		saveCodes(url);
		return readCodes(url);
	}

	private void saveCodes(String url) throws IOException, InterruptedException {
		final String command = makeCommandGitClone(url);
		final Process process = Runtime.getRuntime().exec(command);
		process.waitFor();
		process.destroy();
	}

	private List<File> findJavaFiles(String path) {
		final File directory = new File(path);
		final List<File> files = List.of(Objects.requireNonNull(directory.listFiles()));
		for (File file : files) {
			if (file.isDirectory()) {
				findJavaFiles(file.getPath());
			} else if (file.getName().endsWith(JAVA_EXTENSION)) {
				javaFiles.add(file);
			}
		}
		return javaFiles;
	}

	private List<CodeFile> readCodes(String url) throws IOException {
		final List<File> javaFiles = findJavaFiles(CLONE_PATH);
		final List<CodeFile> codes = new ArrayList<>();
		for (File javaFile : javaFiles) {
			final FileReader fileReader = new FileReader(javaFile);
			final BufferedReader bufferedReader = new BufferedReader(fileReader);
			final CodeFile codeFile = CodeFile.of(getFileContents(bufferedReader), url, javaFile.getName());
			codes.add(codeFile);
		}
		return codes;
	}

	private String getFileContents(BufferedReader bufferedReader) throws IOException {
		final StringBuilder fileContents = new StringBuilder();
		while (true) {
			final String line = bufferedReader.readLine();
			if (line == null) {
				break;
			}
			fileContents.append(line).append(NEW_LINE);
		}
		return fileContents.toString();
	}

	private String makeCommandGitClone(String url) {
		return "git clone " + url + " " + CLONE_PATH + "/" + makeProjectName(url);
	}

	private String makeProjectName(String url) {
		final List<String> splitUrl = List.of(url.split("/"));
		final String projectNameWithExtension = splitUrl.get(splitUrl.size() - 1);
		final List<String> splitProjectName = List.of(projectNameWithExtension.split("\\."));
		return splitProjectName.get(0);
	}
}
