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

    private static final String content = "package com.ecsimsw.springelk.domain;\n"
        + "\n"
        + "import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;\n"
        + "\n"
        + "import java.util.List;\n"
        + "\n"
        + "public interface CodeRepository extends ElasticsearchRepository<Code, String> {\n"
        + "\n"
        + "\tList<Code> findCodeByContentContaining(String keyword);\n"
        + "\n"
        + "\tList<Code> findCodeByContentRegex(String regex);\n"
        + "\n"
        + "\tlong countByClassName(String className);\n"
        + "}\n";

    private static final String className = "CodeRepository";

    @Autowired
    private CodeService codeService;

    public void test() {
        codeService.create(Language.JAVA, "com.ecsimsw.springelk.domain", className, content);
    }
}
