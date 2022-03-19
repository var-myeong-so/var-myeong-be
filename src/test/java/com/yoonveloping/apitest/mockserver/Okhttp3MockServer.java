package com.yoonveloping.apitest.mockserver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.springframework.http.HttpMethod;

public class Okhttp3MockServer implements MockServer {

    private final String host;
    private final int port;

    private MockWebServer mockServer;

    public Okhttp3MockServer(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void start() throws IOException {
        mockServer = new MockWebServer();
        mockServer.start(port);
    }

    @Override
    public void down() throws IOException {
        if (mockServer != null) {
            mockServer.shutdown();
        }
    }

    @Override
    public void requestMapping(HttpMethod httpMethod, String path, String responseBody, int delay) {
        final MockResponse response = new MockResponse()
                .setBody(responseBody)
                .addHeader("Content-Type", "application/json")
                .setBodyDelay(delay, TimeUnit.SECONDS);
        mockServer.enqueue(response);
    }
}
