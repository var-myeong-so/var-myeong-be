package com.varmyeongso.varnames.crawling;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CrawlingService {

	private static final String ADDRESS_PATH = "src/main/resources/address";
	private static final String CLONE_PATH = "src/main/resources/gitsources";
	private static int DIRECTORY_NUMBER = 0;
	private final List<File> javaFiles = new ArrayList<>();

	public List<String> readAddressFile() throws IOException {
		FileReader fileReader = new FileReader(ADDRESS_PATH);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		return getFileContents(bufferedReader);
	}

	private List<String> getFileContents(BufferedReader bufferedReader) throws IOException {
		List<String> fileContents = new ArrayList<>();
		while (true) {
			String line = bufferedReader.readLine();
			if (line == null) {
				break;
			}
			fileContents.add(line);
		}
		return fileContents;
	}

	public void gitClone() throws IOException, InterruptedException {
		Process p;
		List<String> gitAddresses = readAddressFile();
		for (String gitAddress : gitAddresses) {
			String cmd = "git clone " + gitAddress + " " + CLONE_PATH + "/" + DIRECTORY_NUMBER++;
			p = Runtime.getRuntime().exec(cmd);
			p.waitFor();
			System.out.println("exit: " + p.exitValue());
			p.destroy();
		}
	}

	public List<File> findJavaFiles(String path) {
		File directory = new File(path);
		List<File> files = List.of(Objects.requireNonNull(directory.listFiles()));
		for (File file : files) {
			if (file.isDirectory()) {
				findJavaFiles(file.getPath());
			} else if (file.getName().endsWith("java")) {
				javaFiles.add(file);
			}
		}
		return javaFiles;
	}

	public void readCode(String path) throws IOException {
		List<File> javaFiles = findJavaFiles(path);
		for (File javaFile : javaFiles) {
			FileReader fileReader = new FileReader(javaFile);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			System.out.println(getFileContents(bufferedReader));
		}
	}
}
