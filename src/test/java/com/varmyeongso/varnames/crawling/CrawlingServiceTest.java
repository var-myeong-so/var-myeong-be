package com.varmyeongso.varnames.crawling;

import java.io.IOException;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CrawlingServiceTest {

	private final CrawlingService crawlingService = new CrawlingService();

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
}