package com.ecsimsw.springelk.util;

public class CommandFactory {

	private static final String CLONE_PATH = "springboot/src/main/resources/githubcodes";
	private static int DIRECTORY_NUMBER = 0;

	private CommandFactory() { }

	public static String makeCommandGitClone(String url) {
		return "git clone " + url + " " + CLONE_PATH + "/" + DIRECTORY_NUMBER++;
	}
}
