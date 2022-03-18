package com.yoonveloping.apitest.webclient;

import static org.assertj.core.api.Assertions.assertThat;

import com.yoonveloping.apitest.mockserver.MockServer;
import com.yoonveloping.apitest.mockserver.Okhttp3MockServer;
import java.io.IOException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;

class WebclientControllerTest {

    private final String requestUrl = "http://localhost:8888";
    private final MockServer mockServer = new Okhttp3MockServer("http://localhost", 8888);
    private final WebclientController controller = new WebclientController();

    @BeforeEach
    void setUp() throws IOException {
        mockServer.start();
    }

    @AfterEach
    void tearDown() throws IOException {
        mockServer.down();
    }

    @DisplayName("doTest()의 Get 요청과 응답을 확인한다.")
    @Test
    void doTest() {
        String expectedResponse = "ok";
        String path = "/";
        mockServer.requestMapping(HttpMethod.GET, path, expectedResponse, 1);

        String response = controller.doTest(requestUrl + path).block();
        assertThat(response).isEqualTo(expectedResponse);
    }
}
