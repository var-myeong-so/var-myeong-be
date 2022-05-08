package com.ecsimsw.springelk;

import com.ecsimsw.springelk.application.AdminService;
import com.ecsimsw.springelk.domain.Language;
import com.ecsimsw.springelk.dto.CodeFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.annotation.PostConstruct;

@EnableWebMvc
@SpringBootApplication
public class SpringElkApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringElkApplication.class, args);
    }
}

@Component
class TestDummy {
    private static final String path = "https://github.com/ecsimsw/fast-crud/blob/develop/src/main/java/com/ecsimsw/fastcrud/TargetEntity.java";
    private static final String userName = "ecsimsw";
    private static final String projectName = "fast-crud";
    private static final String packageName = "fastcrud";
    private static final String className = "TargetEntity";
    private static final int star = 10;
    private static final String content = "package com.ecsimsw.fastcrud;\n" +
            "\n" +
            "import com.ecsimsw.fastcrud.annotation.CRUD;\n" +
            "import com.ecsimsw.fastcrud.annotation.CrudType;\n" +
            "import com.ecsimsw.fastcrud.exception.FastCrudException;\n" +
            "import java.util.Arrays;\n" +
            "import java.util.List;\n" +
            "import javax.persistence.Entity;\n" +
            "import org.springframework.util.ClassUtils;\n" +
            "\n" +
            "public class TargetEntity {\n" +
            "\n" +
            "    private final static String POSTFIX_REPOSITORY_BEAN_NAME = \"Repository\";\n" +
            "\n" +
            "    private final Class<?> type;\n" +
            "    private final String rootPath;\n" +
            "    private final String repositoryBeanName;\n" +
            "\n" +
            "    public TargetEntity(Object targetObj) {\n" +
            "        if (!targetObj.getClass().isAnnotationPresent(Entity.class)) {\n" +
            "            throw new FastCrudException(\"CRUD annotation must be with @Entity annotation\");\n" +
            "        }\n" +
            "        this.type = targetObj.getClass();\n" +
            "\n" +
            "        final String classShortName = ClassUtils.getShortNameAsProperty(type);\n" +
            "        this.rootPath = rootPath(classShortName);\n" +
            "        this.repositoryBeanName = repositoryBeanName(classShortName);\n" +
            "    }\n" +
            "\n" +
            "    private String rootPath(String classShortName) {\n" +
            "        final String userDefinedRootPath = crud().rootPath();\n" +
            "        return userDefinedRootPath.isEmpty() ? classShortName : userDefinedRootPath;\n" +
            "    }\n" +
            "\n" +
            "    private String repositoryBeanName(String classShortName) {\n" +
            "        final String defaultRepositoryBeanName = classShortName + POSTFIX_REPOSITORY_BEAN_NAME;\n" +
            "        final String userDefinedRepositoryBeanName = crud().repositoryBean();\n" +
            "        return userDefinedRepositoryBeanName.isEmpty() ? defaultRepositoryBeanName : userDefinedRepositoryBeanName;\n" +
            "    }\n" +
            "\n" +
            "    public List<CrudType> excludedTypes() {\n" +
            "        return Arrays.asList(crud().excludeType());\n" +
            "    }\n" +
            "\n" +
            "    public String rootPath() {\n" +
            "        return rootPath;\n" +
            "    }\n" +
            "\n" +
            "    public String repositoryBeanName() {\n" +
            "        return repositoryBeanName;\n" +
            "    }\n" +
            "\n" +
            "    private CRUD crud() {\n" +
            "        return type.getAnnotation(CRUD.class);\n" +
            "    }\n" +
            "\n" +
            "    public Class<?> type() {\n" +
            "        return type;\n" +
            "    }\n" +
            "}";

    @Autowired
    private AdminService adminService;

    @PostConstruct
    public void setUp() {
        final CodeFile codeFile = new CodeFile(className, userName, projectName, packageName, star, path, Language.JAVA, content);
        adminService.storeCode(codeFile);
    }
}
