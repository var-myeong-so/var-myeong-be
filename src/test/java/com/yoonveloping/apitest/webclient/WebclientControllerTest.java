package com.yoonveloping.apitest.webclient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.yoonveloping.apitest.mockserver.MockServer;
import com.yoonveloping.apitest.mockserver.Okhttp3MockServer;
import java.io.IOException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClientResponseException;

class WebclientControllerTest {

    private final MockServer mockServer = new Okhttp3MockServer("http://localhost", 8888);
    private final WebclientController controller = new WebclientController();

    private final String requestPath = "http://localhost:8888/";
    private final String expectedResponse = "ok";

    @BeforeEach
    void setUp() throws IOException {
        mockServer.start();
    }

    @AfterEach
    void tearDown() {
        try {
            mockServer.down();
        } catch (IOException e) {
        }
    }

    @DisplayName("doTest()의 Get 요청과 응답을 확인한다.")
    @Test
    void doTest() {
        mockServer.requestMapping(HttpMethod.GET, requestPath, expectedResponse, 1);

        assertThat(controller.doTest(requestPath).block())
                .isEqualTo(expectedResponse);
    }

    @DisplayName("서버 지연이 있는 경우, n초 후 자동 timeout 처리된다.")
    @Test
    void timeOutTest() {
        mockServer.requestMapping(HttpMethod.GET, requestPath, expectedResponse, 10);

        assertThatThrownBy(() -> controller.doTest(requestPath).block())
                .isInstanceOf(WebClientResponseException.class);
    }
}
