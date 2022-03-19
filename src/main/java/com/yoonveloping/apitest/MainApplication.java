package com.yoonveloping.apitest;

import com.yoonveloping.apitest.webclient.WebclientController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(MainApplication.class, args);

        WebclientController webclientController = ctx.getBean(WebclientController.class);
        webclientController.doTranslate("가져오다");
    }
}
