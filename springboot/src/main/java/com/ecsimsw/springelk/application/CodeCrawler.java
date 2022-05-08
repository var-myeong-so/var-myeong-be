package com.ecsimsw.springelk.application;

import com.ecsimsw.springelk.dto.CodeFile;
import com.ecsimsw.springelk.util.CommandFactory;
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
		readCode();
		return null;
	}

	private void saveCodes(String url) throws IOException, InterruptedException {
		String command = CommandFactory.makeCommandGitClone(url);
		Process process = Runtime.getRuntime().exec(command);
		process.waitFor();
		process.destroy();
	}

	private List<File> findJavaFiles(String path) {
		final File directory = new File(path);
		List<File> files = List.of(Objects.requireNonNull(directory.listFiles()));
		for (File file : files) {
			if (file.isDirectory()) {
				findJavaFiles(file.getPath());
			} else if (file.getName().endsWith(JAVA_EXTENSION)) {
				javaFiles.add(file);
			}
		}
		return javaFiles;
	}

	private List<String> readCode() throws IOException {
		List<File> javaFiles = findJavaFiles(CLONE_PATH);
		List<String> codes = new ArrayList<>();
		for (File javaFile : javaFiles) {
			FileReader fileReader = new FileReader(javaFile);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String codeFile = getFileContents(bufferedReader);
			codes.add(codeFile);
		}
		return codes;
	}

	private String getFileContents(BufferedReader bufferedReader) throws IOException {
		final StringBuilder fileContents = new StringBuilder();
		while (true) {
			String line = bufferedReader.readLine();
			if (line == null) {
				break;
			}
			fileContents.append(line).append(NEW_LINE);
		}
		return fileContents.toString();
	}
}
