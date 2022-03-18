package com.yoonveloping.apitest.webclient;

import static org.assertj.core.api.Assertions.assertThat;

import com.yoonveloping.apitest.ApiTestApplicationTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

class WebclientControllerTest extends ApiTestApplicationTests {

    WebclientController controller = new WebclientController();

    @DisplayName("doTest()의 Get 요청과 응답을 확인한다.")
    @Test
    void doTest() {
        Mono<String> stringMono = controller.doTest(host + "/");
        String block = stringMono.block();
        assertThat(block).isEqualTo(response);
    }
}
