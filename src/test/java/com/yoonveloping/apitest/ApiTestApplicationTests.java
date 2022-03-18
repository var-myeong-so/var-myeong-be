package com.yoonveloping.apitest;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

import io.netty.handler.codec.http.HttpMethod;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockserver.integration.ClientAndServer;

public abstract class ApiTestApplicationTests {

    private ClientAndServer mockServer;

    protected final String response = "ok";
    protected final int port = 8888;
    protected final String host = "http://localhost:"+port;

    @BeforeEach
    void setUp() {
        mockServer = ClientAndServer.startClientAndServer(port);
        getMapping("/", 0);
    }

    void getMapping(String path, int delay) {
        mockServer.when(request(path).withMethod(HttpMethod.GET.name()))
                .respond(response(response).withDelay(TimeUnit.SECONDS, delay));
    }

    @AfterEach
    void shutDown() {
        mockServer.stop();
    }
}
