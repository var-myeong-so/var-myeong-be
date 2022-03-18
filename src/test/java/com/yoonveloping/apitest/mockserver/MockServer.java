package com.yoonveloping.apitest.mockserver;

import java.io.IOException;
import org.springframework.http.HttpMethod;

public interface MockServer {
    void start() throws IOException;
    void down() throws IOException;
    void requestMapping(HttpMethod httpMethod, String path, String response, int delay);
}
