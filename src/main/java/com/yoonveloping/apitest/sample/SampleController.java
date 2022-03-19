package com.yoonveloping.apitest.sample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    public static final String SAMPLE_API_PATH = "/sample";

    @GetMapping(SAMPLE_API_PATH)
    public String sample() {
        return "hi";
    }

}
