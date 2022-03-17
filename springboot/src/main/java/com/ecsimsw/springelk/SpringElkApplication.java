package com.ecsimsw.springelk;

import com.ecsimsw.springelk.application.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class SpringElkApplication {

    public static void main(String[] args) {
        final ConfigurableApplicationContext ctx = SpringApplication.run(SpringElkApplication.class, args);

        final TestDummy dummy = ctx.getBean(TestDummy.class);
        dummy.test();
    }

}

@Component
class TestDummy {

    @Autowired
    private PersonService personService;

    public void test() {
        personService.testCreate();
    }
}
