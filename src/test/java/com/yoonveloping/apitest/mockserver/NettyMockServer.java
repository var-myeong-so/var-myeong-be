package com.yoonveloping.apitest.mockserver;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

import java.util.concurrent.TimeUnit;
import org.mockserver.integration.ClientAndServer;
import org.springframework.http.HttpMethod;

public class NettyMockServer implements MockServer {

    private final String host;
    private final int port;

    private ClientAndServer mockServer;

    public NettyMockServer(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void start() {
        mockServer = ClientAndServer.startClientAndServer(port);
    }

    @Override
    public void down() {
        mockServer.stop();
    }

    @Override
    public void requestMapping(HttpMethod httpMethod, String path, String response, int delay) {
        mockServer.when(request(path).withMethod(httpMethod.name()))
                .respond(response(response).withDelay(TimeUnit.SECONDS, delay));
    }
}
