package com.varmyeongso.varnames.crawling;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CrawlingServiceTest {

	private final CrawlingService crawlingService = new CrawlingService();
	private static final String CLONE_PATH = "src/main/resources/gitsources";

	@DisplayName("파일 읽기 테스트")
	@Test
	void readFileTest() throws IOException {
		List<String> read = crawlingService.readAddressFile();
		for (String s : read) {
			Assertions.assertThat(s)
				.isEqualTo("https://github.com/spring-projects/spring-boot.git");
		}
	}

	@DisplayName("깃 클론 테스트")
	@Test
	void gitCloneTest() throws IOException, InterruptedException {
		crawlingService.gitClone();
	}

	@DisplayName("자바 파일 찾기 테스트")
	@Test
	void findJavaFilesTest() {
		List<File> javaFiles = crawlingService.findJavaFiles(CLONE_PATH);
		javaFiles.forEach(System.out::println);
	}

	@DisplayName("자바 파일 코드 읽기 테스트")
	@Test
	void readCodeTest() throws IOException {
		crawlingService.readCode(CLONE_PATH);
	}
}