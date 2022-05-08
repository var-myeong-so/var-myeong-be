package com.ecsimsw.springelk.application;

import com.ecsimsw.springelk.dto.CodeFile;
import java.io.IOException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodeCrawler {

    private static final String CLONE_PATH = "springboot/src/main/resources/githubcodes";
    private static Integer DIRECTORY_NUMBER = 0;

    public List<CodeFile> execute(String url) throws IOException, InterruptedException {
        saveCodes(url);
		return null;
    }

    private void saveCodes(String url) throws IOException, InterruptedException {
        String command = makeCommandGitClone(url);
        Process process = Runtime.getRuntime().exec(command);
        process.waitFor();
        System.out.println("exit: " + process.exitValue());
        process.destroy();
    }

    private String makeCommandGitClone(String url) {
        return "git clone " + url + " " + CLONE_PATH + "/" + DIRECTORY_NUMBER++;
    }
}
