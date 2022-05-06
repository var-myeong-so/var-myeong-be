package com.ecsimsw.springelk;

import com.ecsimsw.springelk.application.CodeService;
import com.ecsimsw.springelk.domain.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class SpringElkApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringElkApplication.class, args);
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

    @PostConstruct
    public void test() {
        codeService.create(Language.JAVA, "com.ecsimsw.springelk.domain", className, content);
    }
}
