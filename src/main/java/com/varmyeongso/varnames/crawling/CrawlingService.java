package com.varmyeongso.varnames.crawling;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CrawlingService {

	private static final String ADDRESS_PATH = "src/main/resources/address";
	private static final String CLONE_PATH = "src/main/resources/gitsources";
	private static int DIRECTORY_NUMBER = 0;

	public List<String> readAddressFile() throws IOException {
		FileReader fileReader = new FileReader(ADDRESS_PATH);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
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
}
