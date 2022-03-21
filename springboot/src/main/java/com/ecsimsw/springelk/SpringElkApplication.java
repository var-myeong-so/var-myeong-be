package com.ecsimsw.springelk;

import com.ecsimsw.springelk.application.CodeService;
import com.ecsimsw.springelk.domain.Language;
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

    private static final String content = "private String id;\n"
        + "\n"
        + "\tprivate Language language;\n"
        + "\n"
        + "\tpublic Code(Language language) {\n"
        + "\t\tthis.language = language;\n"
        + "\t}\n"
        + "\n"
        + "\tpublic String getId() {\n"
        + "\t\treturn id;\n"
        + "\t}\n"
        + "\n"
        + "\tpublic void setId(String id) {\n"
        + "\t\tthis.id = id;\n"
        + "\t}";

    private static final String className = "Code";

    @Autowired
    private CodeService codeService;

    public void test() {
        codeService.create(Language.JAVA, "com.ecsimsw.springelk.domain", className, content);
    }
}
