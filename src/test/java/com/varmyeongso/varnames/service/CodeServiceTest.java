package com.varmyeongso.varnames.service;

import com.varmyeongso.varnames.domain.Code;
import com.varmyeongso.varnames.domain.CodeRepository;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CodeServiceTest {

	@Mock
	private CodeRepository codeRepository;

	@InjectMocks
	private CodeService codeService = new CodeService(this.codeRepository);

	@Test
	void test() {
		List<Code> codes = createCodes(1);

		try {
			Method parse = codeService.getClass()
				.getDeclaredMethod("parse", List.class, String.class);
			parse.setAccessible(true);
			List<Code> invoke = (List<Code>) parse.invoke(codeService, codes,
				"public SpringApplication build(String... args) {");
			for (Code code : invoke) {
				String code1 = code.getCode();
				System.out.println("code1 = " + code1);
			}
		} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	List<Code> createCodes(int count) {

		final List<Code> codes = new ArrayList<>();

		for(int i =0; i< count; i++) {
			codes.add(makeCode());
		}

		codeRepository.saveAll(codes);
		return codes;
	}

	Code makeCode() {
		return new Code(
			"spring-projects/spring-boot",
			"org.springframework.boot.builder",
			"SpringApplicationBuilder.java",
			"java",
			"https://github.com/spring-projects/spring-boot/blob/main/spring-boot-project/spring-boot/src/main/java/org/springframework/boot/builder/SpringApplicationBuilder.java",
			"/*\n" +
				" * Copyright 2012-2022 the original author or authors.\n" +
				" *\n" +
				" * Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
				" * you may not use this file except in compliance with the License.\n" +
				" * You may obtain a copy of the License at\n" +
				" *\n" +
				" *      https://www.apache.org/licenses/LICENSE-2.0\n" +
				" *\n" +
				" * Unless required by applicable law or agreed to in writing, software\n" +
				" * distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
				" * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
				" * See the License for the specific language governing permissions and\n" +
				" * limitations under the License.\n" +
				" */\n" +
				"\n" +
				"package org.springframework.boot.builder;\n" +
				"\n" +
				"import java.util.Arrays;\n" +
				"import java.util.Collection;\n" +
				"import java.util.Collections;\n" +
				"import java.util.HashMap;\n" +
				"import java.util.LinkedHashMap;\n" +
				"import java.util.LinkedHashSet;\n" +
				"import java.util.Map;\n" +
				"import java.util.Properties;\n" +
				"import java.util.Set;\n" +
				"import java.util.concurrent.atomic.AtomicBoolean;\n" +
				"\n" +
				"import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;\n" +
				"import org.springframework.beans.factory.support.BeanNameGenerator;\n" +
				"import org.springframework.boot.ApplicationContextFactory;\n" +
				"import org.springframework.boot.Banner;\n" +
				"import org.springframework.boot.BootstrapRegistry;\n" +
				"import org.springframework.boot.BootstrapRegistryInitializer;\n" +
				"import org.springframework.boot.SpringApplication;\n" +
				"import org.springframework.boot.WebApplicationType;\n" +
				"import org.springframework.boot.convert.ApplicationConversionService;\n" +
				"import org.springframework.context.ApplicationContext;\n" +
				"import org.springframework.context.ApplicationContextInitializer;\n" +
				"import org.springframework.context.ApplicationListener;\n" +
				"import org.springframework.context.ConfigurableApplicationContext;\n" +
				"import org.springframework.core.env.ConfigurableEnvironment;\n" +
				"import org.springframework.core.env.Environment;\n" +
				"import org.springframework.core.io.ResourceLoader;\n" +
				"import org.springframework.core.metrics.ApplicationStartup;\n" +
				"import org.springframework.util.StringUtils;\n" +
				"\n" +
				"/**\n" +
				" * Builder for {@link SpringApplication} and {@link ApplicationContext} instances with\n" +
				" * convenient fluent API and context hierarchy support. Simple example of a context\n" +
				" * hierarchy:\n" +
				" *\n" +
				" * <pre class=\"code\">\n" +
				" * new SpringApplicationBuilder(ParentConfig.class).child(ChildConfig.class).run(args);\n" +
				" * </pre>\n" +
				" *\n" +
				" * Another common use case is setting active profiles and default properties to set up the\n" +
				" * environment for an application:\n" +
				" *\n" +
				" * <pre class=\"code\">\n" +
				" * new SpringApplicationBuilder(Application.class).profiles(&quot;server&quot;)\n" +
				" * \t\t.properties(&quot;transport=local&quot;).run(args);\n" +
				" * </pre>\n" +
				" *\n" +
				" * <p>\n" +
				" * If your needs are simpler, consider using the static convenience methods in\n" +
				" * SpringApplication instead.\n" +
				" *\n" +
				" * @author Dave Syer\n" +
				" * @author Andy Wilkinson\n" +
				" * @since 1.0.0\n" +
				" * @see SpringApplication\n" +
				" */\n" +
				"public class SpringApplicationBuilder {\n" +
				"\n" +
				"\tprivate final SpringApplication application;\n" +
				"\n" +
				"\tprivate ConfigurableApplicationContext context;\n" +
				"\n" +
				"\tprivate SpringApplicationBuilder parent;\n" +
				"\n" +
				"\tprivate final AtomicBoolean running = new AtomicBoolean();\n" +
				"\n" +
				"\tprivate final Set<Class<?>> sources = new LinkedHashSet<>();\n" +
				"\n" +
				"\tprivate final Map<String, Object> defaultProperties = new LinkedHashMap<>();\n" +
				"\n" +
				"\tprivate ConfigurableEnvironment environment;\n" +
				"\n" +
				"\tprivate Set<String> additionalProfiles = new LinkedHashSet<>();\n" +
				"\n" +
				"\tprivate boolean registerShutdownHookApplied;\n" +
				"\n" +
				"\tprivate boolean configuredAsChild = false;\n" +
				"\n" +
				"\tpublic SpringApplicationBuilder(Class<?>... sources) {\n" +
				"\t\tthis(null, sources);\n" +
				"\t}\n" +
				"\n" +
				"\tpublic SpringApplicationBuilder(ResourceLoader resourceLoader, Class<?>... sources) {\n" +
				"\t\tthis.application = createSpringApplication(resourceLoader, sources);\n" +
				"\t}\n" +
				"\n" +
				"\t/**\n" +
				"\t * Creates a new {@link SpringApplication} instance from the given sources using the\n" +
				"\t * given {@link ResourceLoader}. Subclasses may override in order to provide a custom\n" +
				"\t * subclass of {@link SpringApplication}.\n" +
				"\t * @param resourceLoader the resource loader (can be null)\n" +
				"\t * @param sources the sources\n" +
				"\t * @return the {@link SpringApplication} instance\n" +
				"\t * @since 2.6.0\n" +
				"\t */\n" +
				"\tprotected SpringApplication createSpringApplication(ResourceLoader resourceLoader, Class<?>... sources) {\n" +
				"\t\treturn new SpringApplication(resourceLoader, sources);\n" +
				"\t}\n" +
				"\n" +
				"\t/**\n" +
				"\t * Accessor for the current application context.\n" +
				"\t * @return the current application context (or null if not yet running)\n" +
				"\t */\n" +
				"\tpublic ConfigurableApplicationContext context() {\n" +
				"\t\treturn this.context;\n" +
				"\t}\n" +
				"\n" +
				"\t/**\n" +
				"\t * Accessor for the current application.\n" +
				"\t * @return the current application (never null)\n" +
				"\t */\n" +
				"\tpublic SpringApplication application() {\n" +
				"\t\treturn this.application;\n" +
				"\t}\n" +
				"\n" +
				"\t/**\n" +
				"\t * Create an application context (and its parent if specified) with the command line\n" +
				"\t * args provided. The parent is run first with the same arguments if has not yet been\n" +
				"\t * started.\n" +
				"\t * @param args the command line arguments\n" +
				"\t * @return an application context created from the current state\n" +
				"\t */\n" +
				"\tpublic ConfigurableApplicationContext run(String... args) {\n" +
				"\t\tif (this.running.get()) {\n" +
				"\t\t\t// If already created we just return the existing context\n" +
				"\t\t\treturn this.context;\n" +
				"\t\t}\n" +
				"\t\tconfigureAsChildIfNecessary(args);\n" +
				"\t\tif (this.running.compareAndSet(false, true)) {\n" +
				"\t\t\tsynchronized (this.running) {\n" +
				"\t\t\t\t// If not already running copy the sources over and then run.\n" +
				"\t\t\t\tthis.context = build().run(args);\n" +
				"\t\t\t}\n" +
				"\t\t}\n" +
				"\t\treturn this.context;\n" +
				"\t}\n" +
				"\n" +
				"\tprivate void configureAsChildIfNecessary(String... args) {\n" +
				"\t\tif (this.parent != null && !this.configuredAsChild) {\n" +
				"\t\t\tthis.configuredAsChild = true;\n" +
				"\t\t\tif (!this.registerShutdownHookApplied) {\n" +
				"\t\t\t\tthis.application.setRegisterShutdownHook(false);\n" +
				"\t\t\t}\n" +
				"\t\t\tinitializers(new ParentContextApplicationContextInitializer(this.parent.run(args)));\n" +
				"\t\t}\n" +
				"\t}\n" +
				"\n" +
				"\t/**\n" +
				"\t * Returns a fully configured {@link SpringApplication} that is ready to run.\n" +
				"\t * @return the fully configured {@link SpringApplication}.\n" +
				"\t */\n" +
				"\tpublic SpringApplication build() {\n" +
				"\t\treturn build(new String[0]);\n" +
				"\t}\n" +
				"\n" +
				"\t/**\n" +
				"\t * Returns a fully configured {@link SpringApplication} that is ready to run. Any\n" +
				"\t * parent that has been configured will be run with the given {@code args}.\n" +
				"\t * @param args the parent's args\n" +
				"\t * @return the fully configured {@link SpringApplication}.\n" +
				"\t */\n" +
				"\tpublic SpringApplication build(String... args) {\n" +
				"\t\tconfigureAsChildIfNecessary(args);\n" +
				"\t\tthis.application.addPrimarySources(this.sources);\n" +
				"\t\treturn this.application;\n" +
				"\t}\n" +
				"\n" +
				"\t/**\n" +
				"\t * Create a child application with the provided sources. Default args and environment\n" +
				"\t * are copied down into the child, but everything else is a clean sheet.\n" +
				"\t * @param sources the sources for the application (Spring configuration)\n" +
				"\t * @return the child application builder\n" +
				"\t */\n" +
				"\tpublic SpringApplicationBuilder child(Class<?>... sources) {\n" +
				"\t\tSpringApplicationBuilder child = new SpringApplicationBuilder();\n" +
				"\t\tchild.sources(sources);\n" +
				"\n" +
				"\t\t// Copy environment stuff from parent to child\n" +
				"\t\tchild.properties(this.defaultProperties).environment(this.environment)\n" +
				"\t\t\t\t.additionalProfiles(this.additionalProfiles);\n" +
				"\t\tchild.parent = this;\n" +
				"\n" +
				"\t\t// It's not possible if embedded web server are enabled to support web contexts as\n" +
				"\t\t// parents because the servlets cannot be initialized at the right point in\n" +
				"\t\t// lifecycle.\n" +
				"\t\tweb(WebApplicationType.NONE);\n" +
				"\n" +
				"\t\t// Probably not interested in multiple banners\n" +
				"\t\tbannerMode(Banner.Mode.OFF);\n" +
				"\n" +
				"\t\t// Make sure sources get copied over\n" +
				"\t\tthis.application.addPrimarySources(this.sources);\n" +
				"\n" +
				"\t\treturn child;\n" +
				"\t}\n" +
				"\n" +
				"\t/**\n" +
				"\t * Add a parent application with the provided sources. Default args and environment\n" +
				"\t * are copied up into the parent, but everything else is a clean sheet.\n" +
				"\t * @param sources the sources for the application (Spring configuration)\n" +
				"\t * @return the parent builder\n" +
				"\t */\n" +
				"\tpublic SpringApplicationBuilder parent(Class<?>... sources) {\n" +
				"\t\tif (this.parent == null) {\n" +
				"\t\t\tthis.parent = new SpringApplicationBuilder(sources).web(WebApplicationType.NONE)\n" +
				"\t\t\t\t\t.properties(this.defaultProperties).environment(this.environment);\n" +
				"\t\t}\n" +
				"\t\telse {\n" +
				"\t\t\tthis.parent.sources(sources);\n" +
				"\t\t}\n" +
				"\t\treturn this.parent;\n" +
				"\t}\n" +
				"\n" +
				"\tprivate SpringApplicationBuilder runAndExtractParent(String... args) {\n" +
				"\t\tif (this.context == null) {\n" +
				"\t\t\trun(args);\n" +
				"\t\t}\n" +
				"\t\tif (this.parent != null) {\n" +
				"\t\t\treturn this.parent;\n" +
				"\t\t}\n" +
				"\t\tthrow new IllegalStateException(\n" +
				"\t\t\t\t\"No parent defined yet (please use the other overloaded parent methods to set one)\");\n" +
				"\t}\n" +
				"\n" +
				"\t/**\n" +
				"\t * Add an already running parent context to an existing application.\n" +
				"\t * @param parent the parent context\n" +
				"\t * @return the current builder (not the parent)\n" +
				"\t */\n" +
				"\tpublic SpringApplicationBuilder parent(ConfigurableApplicationContext parent) {\n" +
				"\t\tthis.parent = new SpringApplicationBuilder();\n" +
				"\t\tthis.parent.context = parent;\n" +
				"\t\tthis.parent.running.set(true);\n" +
				"\t\treturn this;\n" +
				"\t}\n" +
				"\n" +
				"\t/**\n" +
				"\t * Create a sibling application (one with the same parent). A side effect of calling\n" +
				"\t * this method is that the current application (and its parent) are started without\n" +
				"\t * any arguments if they are not already running. To supply arguments when starting\n" +
				"\t * the current application and its parent use {@link #sibling(Class[], String...)}\n" +
				"\t * instead.\n" +
				"\t * @param sources the sources for the application (Spring configuration)\n" +
				"\t * @return the new sibling builder\n" +
				"\t */\n" +
				"\tpublic SpringApplicationBuilder sibling(Class<?>... sources) {\n" +
				"\t\treturn runAndExtractParent().child(sources);\n" +
				"\t}\n" +
				"\n" +
				"\t/**\n" +
				"\t * Create a sibling application (one with the same parent). A side effect of calling\n" +
				"\t * this method is that the current application (and its parent) are started if they\n" +
				"\t * are not already running.\n" +
				"\t * @param sources the sources for the application (Spring configuration)\n" +
				"\t * @param args the command line arguments to use when starting the current app and its\n" +
				"\t * parent\n" +
				"\t * @return the new sibling builder\n" +
				"\t */\n" +
				"\tpublic SpringApplicationBuilder sibling(Class<?>[] sources, String... args) {\n" +
				"\t\treturn runAndExtractParent(args).child(sources);\n" +
				"\t}\n" +
				"\n" +
				"\t/**\n" +
				"\t * Explicitly set the factory used to create the application context.\n" +
				"\t * @param factory the factory to use\n" +
				"\t * @return the current builder\n" +
				"\t * @since 2.4.0\n" +
				"\t */\n" +
				"\tpublic SpringApplicationBuilder contextFactory(ApplicationContextFactory factory) {\n" +
				"\t\tthis.application.setApplicationContextFactory(factory);\n" +
				"\t\treturn this;\n" +
				"\t}\n" +
				"\n" +
				"\t/**\n" +
				"\t * Add more sources (configuration classes and components) to this application.\n" +
				"\t * @param sources the sources to add\n" +
				"\t * @return the current builder\n" +
				"\t */\n" +
				"\tpublic SpringApplicationBuilder sources(Class<?>... sources) {\n" +
				"\t\tthis.sources.addAll(new LinkedHashSet<>(Arrays.asList(sources)));\n" +
				"\t\treturn this;\n" +
				"\t}\n" +
				"\n" +
				"\t/**\n" +
				"\t * Flag to explicitly request a specific type of web application. Auto-detected based\n" +
				"\t * on the classpath if not set.\n" +
				"\t * @param webApplicationType the type of web application\n" +
				"\t * @return the current builder\n" +
				"\t * @since 2.0.0\n" +
				"\t */\n" +
				"\tpublic SpringApplicationBuilder web(WebApplicationType webApplicationType) {\n" +
				"\t\tthis.application.setWebApplicationType(webApplicationType);\n" +
				"\t\treturn this;\n" +
				"\t}\n" +
				"\n" +
				"\t/**\n" +
				"\t * Flag to indicate the startup information should be logged.\n" +
				"\t * @param logStartupInfo the flag to set. Default true.\n" +
				"\t * @return the current builder\n" +
				"\t */\n" +
				"\tpublic SpringApplicationBuilder logStartupInfo(boolean logStartupInfo) {\n" +
				"\t\tthis.application.setLogStartupInfo(logStartupInfo);\n" +
				"\t\treturn this;\n" +
				"\t}\n" +
				"\n" +
				"\t/**\n" +
				"\t * Sets the {@link Banner} instance which will be used to print the banner when no\n" +
				"\t * static banner file is provided.\n" +
				"\t * @param banner the banner to use\n" +
				"\t * @return the current builder\n" +
				"\t */\n" +
				"\tpublic SpringApplicationBuilder banner(Banner banner) {\n" +
				"\t\tthis.application.setBanner(banner);\n" +
				"\t\treturn this;\n" +
				"\t}\n" +
				"\n" +
				"\tpublic SpringApplicationBuilder bannerMode(Banner.Mode bannerMode) {\n" +
				"\t\tthis.application.setBannerMode(bannerMode);\n" +
				"\t\treturn this;\n" +
				"\t}\n" +
				"\n" +
				"\t/**\n" +
				"\t * Sets if the application is headless and should not instantiate AWT. Defaults to\n" +
				"\t * {@code true} to prevent java icons appearing.\n" +
				"\t * @param headless if the application is headless\n" +
				"\t * @return the current builder\n" +
				"\t */\n" +
				"\tpublic SpringApplicationBuilder headless(boolean headless) {\n" +
				"\t\tthis.application.setHeadless(headless);\n" +
				"\t\treturn this;\n" +
				"\t}\n" +
				"\n" +
				"\t/**\n" +
				"\t * Sets if the created {@link ApplicationContext} should have a shutdown hook\n" +
				"\t * registered.\n" +
				"\t * @param registerShutdownHook if the shutdown hook should be registered\n" +
				"\t * @return the current builder\n" +
				"\t */\n" +
				"\tpublic SpringApplicationBuilder registerShutdownHook(boolean registerShutdownHook) {\n" +
				"\t\tthis.registerShutdownHookApplied = true;\n" +
				"\t\tthis.application.setRegisterShutdownHook(registerShutdownHook);\n" +
				"\t\treturn this;\n" +
				"\t}\n" +
				"\n" +
				"\t/**\n" +
				"\t * Fixes the main application class that is used to anchor the startup messages.\n" +
				"\t * @param mainApplicationClass the class to use.\n" +
				"\t * @return the current builder\n" +
				"\t */\n" +
				"\tpublic SpringApplicationBuilder main(Class<?> mainApplicationClass) {\n" +
				"\t\tthis.application.setMainApplicationClass(mainApplicationClass);\n" +
				"\t\treturn this;\n" +
				"\t}\n" +
				"\n" +
				"\t/**\n" +
				"\t * Flag to indicate that command line arguments should be added to the environment.\n" +
				"\t * @param addCommandLineProperties the flag to set. Default true.\n" +
				"\t * @return the current builder\n" +
				"\t */\n" +
				"\tpublic SpringApplicationBuilder addCommandLineProperties(boolean addCommandLineProperties) {\n" +
				"\t\tthis.application.setAddCommandLineProperties(addCommandLineProperties);\n" +
				"\t\treturn this;\n" +
				"\t}\n" +
				"\n" +
				"\t/**\n" +
				"\t * Flag to indicate if the {@link ApplicationConversionService} should be added to the\n" +
				"\t * application context's {@link Environment}.\n" +
				"\t * @param addConversionService if the conversion service should be added.\n" +
				"\t * @return the current builder\n" +
				"\t * @since 2.1.0\n" +
				"\t */\n" +
				"\tpublic SpringApplicationBuilder setAddConversionService(boolean addConversionService) {\n" +
				"\t\tthis.application.setAddConversionService(addConversionService);\n" +
				"\t\treturn this;\n" +
				"\t}\n" +
				"\n" +
				"\t/**\n" +
				"\t * Adds {@link BootstrapRegistryInitializer} instances that can be used to initialize\n" +
				"\t * the {@link BootstrapRegistry}.\n" +
				"\t * @param bootstrapRegistryInitializer the bootstrap registry initializer to add\n" +
				"\t * @return the current builder\n" +
				"\t * @since 2.4.5\n" +
				"\t */\n" +
				"\tpublic SpringApplicationBuilder addBootstrapRegistryInitializer(\n" +
				"\t\t\tBootstrapRegistryInitializer bootstrapRegistryInitializer) {\n" +
				"\t\tthis.application.addBootstrapRegistryInitializer(bootstrapRegistryInitializer);\n" +
				"\t\treturn this;\n" +
				"\t}\n" +
				"\n" +
				"\t/**\n" +
				"\t * Flag to control whether the application should be initialized lazily.\n" +
				"\t * @param lazyInitialization the flag to set. Defaults to false.\n" +
				"\t * @return the current builder\n" +
				"\t * @since 2.2\n" +
				"\t */\n" +
				"\tpublic SpringApplicationBuilder lazyInitialization(boolean lazyInitialization) {\n" +
				"\t\tthis.application.setLazyInitialization(lazyInitialization);\n" +
				"\t\treturn this;\n" +
				"\t}\n" +
				"\n" +
				"\t/**\n" +
				"\t * Default properties for the environment in the form {@code key=value} or\n" +
				"\t * {@code key:value}. Multiple calls to this method are cumulative and will not clear\n" +
				"\t * any previously set properties.\n" +
				"\t * @param defaultProperties the properties to set.\n" +
				"\t * @return the current builder\n" +
				"\t * @see SpringApplicationBuilder#properties(Properties)\n" +
				"\t * @see SpringApplicationBuilder#properties(Map)\n" +
				"\t */\n" +
				"\tpublic SpringApplicationBuilder properties(String... defaultProperties) {\n" +
				"\t\treturn properties(getMapFromKeyValuePairs(defaultProperties));\n" +
				"\t}\n" +
				"\n" +
				"\tprivate Map<String, Object> getMapFromKeyValuePairs(String[] properties) {\n" +
				"\t\tMap<String, Object> map = new HashMap<>();\n" +
				"\t\tfor (String property : properties) {\n" +
				"\t\t\tint index = lowestIndexOf(property, \":\", \"=\");\n" +
				"\t\t\tString key = (index > 0) ? property.substring(0, index) : property;\n" +
				"\t\t\tString value = (index > 0) ? property.substring(index + 1) : \"\";\n" +
				"\t\t\tmap.put(key, value);\n" +
				"\t\t}\n" +
				"\t\treturn map;\n" +
				"\t}\n" +
				"\n" +
				"\tprivate int lowestIndexOf(String property, String... candidates) {\n" +
				"\t\tint index = -1;\n" +
				"\t\tfor (String candidate : candidates) {\n" +
				"\t\t\tint candidateIndex = property.indexOf(candidate);\n" +
				"\t\t\tif (candidateIndex > 0) {\n" +
				"\t\t\t\tindex = (index != -1) ? Math.min(index, candidateIndex) : candidateIndex;\n" +
				"\t\t\t}\n" +
				"\t\t}\n" +
				"\t\treturn index;\n" +
				"\t}\n" +
				"\n" +
				"\t/**\n" +
				"\t * Default properties for the environment.Multiple calls to this method are cumulative\n" +
				"\t * and will not clear any previously set properties.\n" +
				"\t * @param defaultProperties the properties to set.\n" +
				"\t * @return the current builder\n" +
				"\t * @see SpringApplicationBuilder#properties(String...)\n" +
				"\t * @see SpringApplicationBuilder#properties(Map)\n" +
				"\t */\n" +
				"\tpublic SpringApplicationBuilder properties(Properties defaultProperties) {\n" +
				"\t\treturn properties(getMapFromProperties(defaultProperties));\n" +
				"\t}\n" +
				"\n" +
				"\tprivate Map<String, Object> getMapFromProperties(Properties properties) {\n" +
				"\t\tMap<String, Object> map = new HashMap<>();\n" +
				"\t\tfor (Object key : Collections.list(properties.propertyNames())) {\n" +
				"\t\t\tmap.put((String) key, properties.get(key));\n" +
				"\t\t}\n" +
				"\t\treturn map;\n" +
				"\t}\n" +
				"\n" +
				"\t/**\n" +
				"\t * Default properties for the environment. Multiple calls to this method are\n" +
				"\t * cumulative and will not clear any previously set properties.\n" +
				"\t * @param defaults the default properties\n" +
				"\t * @return the current builder\n" +
				"\t * @see SpringApplicationBuilder#properties(String...)\n" +
				"\t * @see SpringApplicationBuilder#properties(Properties)\n" +
				"\t */\n" +
				"\tpublic SpringApplicationBuilder properties(Map<String, Object> defaults) {\n" +
				"\t\tthis.defaultProperties.putAll(defaults);\n" +
				"\t\tthis.application.setDefaultProperties(this.defaultProperties);\n" +
				"\t\tif (this.parent != null) {\n" +
				"\t\t\tthis.parent.properties(this.defaultProperties);\n" +
				"\t\t\tthis.parent.environment(this.environment);\n" +
				"\t\t}\n" +
				"\t\treturn this;\n" +
				"\t}\n" +
				"\n" +
				"\t/**\n" +
				"\t * Add to the active Spring profiles for this app (and its parent and children).\n" +
				"\t * @param profiles the profiles to add.\n" +
				"\t * @return the current builder\n" +
				"\t */\n" +
				"\tpublic SpringApplicationBuilder profiles(String... profiles) {\n" +
				"\t\tthis.additionalProfiles.addAll(Arrays.asList(profiles));\n" +
				"\t\tthis.application.setAdditionalProfiles(StringUtils.toStringArray(this.additionalProfiles));\n" +
				"\t\treturn this;\n" +
				"\t}\n" +
				"\n" +
				"\tprivate SpringApplicationBuilder additionalProfiles(Collection<String> additionalProfiles) {\n" +
				"\t\tthis.additionalProfiles = new LinkedHashSet<>(additionalProfiles);\n" +
				"\t\tthis.application.setAdditionalProfiles(StringUtils.toStringArray(this.additionalProfiles));\n" +
				"\t\treturn this;\n" +
				"\t}\n" +
				"\n" +
				"\t/**\n" +
				"\t * Bean name generator for automatically generated bean names in the application\n" +
				"\t * context.\n" +
				"\t * @param beanNameGenerator the generator to set.\n" +
				"\t * @return the current builder\n" +
				"\t */\n" +
				"\tpublic SpringApplicationBuilder beanNameGenerator(BeanNameGenerator beanNameGenerator) {\n" +
				"\t\tthis.application.setBeanNameGenerator(beanNameGenerator);\n" +
				"\t\treturn this;\n" +
				"\t}\n" +
				"\n" +
				"\t/**\n" +
				"\t * Environment for the application context.\n" +
				"\t * @param environment the environment to set.\n" +
				"\t * @return the current builder\n" +
				"\t */\n" +
				"\tpublic SpringApplicationBuilder environment(ConfigurableEnvironment environment) {\n" +
				"\t\tthis.application.setEnvironment(environment);\n" +
				"\t\tthis.environment = environment;\n" +
				"\t\treturn this;\n" +
				"\t}\n" +
				"\n" +
				"\t/**\n" +
				"\t * Prefix that should be applied when obtaining configuration properties from the\n" +
				"\t * system environment.\n" +
				"\t * @param environmentPrefix the environment property prefix to set\n" +
				"\t * @return the current builder\n" +
				"\t * @since 2.5.0\n" +
				"\t */\n" +
				"\tpublic SpringApplicationBuilder environmentPrefix(String environmentPrefix) {\n" +
				"\t\tthis.application.setEnvironmentPrefix(environmentPrefix);\n" +
				"\t\treturn this;\n" +
				"\t}\n" +
				"\n" +
				"\t/**\n" +
				"\t * {@link ResourceLoader} for the application context. If a custom class loader is\n" +
				"\t * needed, this is where it would be added.\n" +
				"\t * @param resourceLoader the resource loader to set.\n" +
				"\t * @return the current builder\n" +
				"\t */\n" +
				"\tpublic SpringApplicationBuilder resourceLoader(ResourceLoader resourceLoader) {\n" +
				"\t\tthis.application.setResourceLoader(resourceLoader);\n" +
				"\t\treturn this;\n" +
				"\t}\n" +
				"\n" +
				"\t/**\n" +
				"\t * Add some initializers to the application (applied to the {@link ApplicationContext}\n" +
				"\t * before any bean definitions are loaded).\n" +
				"\t * @param initializers some initializers to add\n" +
				"\t * @return the current builder\n" +
				"\t */\n" +
				"\tpublic SpringApplicationBuilder initializers(ApplicationContextInitializer<?>... initializers) {\n" +
				"\t\tthis.application.addInitializers(initializers);\n" +
				"\t\treturn this;\n" +
				"\t}\n" +
				"\n" +
				"\t/**\n" +
				"\t * Add some listeners to the application (listening for SpringApplication events as\n" +
				"\t * well as regular Spring events once the context is running). Any listeners that are\n" +
				"\t * also {@link ApplicationContextInitializer} will be added to the\n" +
				"\t * {@link #initializers(ApplicationContextInitializer...) initializers} automatically.\n" +
				"\t * @param listeners some listeners to add\n" +
				"\t * @return the current builder\n" +
				"\t */\n" +
				"\tpublic SpringApplicationBuilder listeners(ApplicationListener<?>... listeners) {\n" +
				"\t\tthis.application.addListeners(listeners);\n" +
				"\t\treturn this;\n" +
				"\t}\n" +
				"\n" +
				"\t/**\n" +
				"\t * Configure the {@link ApplicationStartup} to be used with the\n" +
				"\t * {@link ApplicationContext} for collecting startup metrics.\n" +
				"\t * @param applicationStartup the application startup to use\n" +
				"\t * @return the current builder\n" +
				"\t * @since 2.4.0\n" +
				"\t */\n" +
				"\tpublic SpringApplicationBuilder applicationStartup(ApplicationStartup applicationStartup) {\n" +
				"\t\tthis.application.setApplicationStartup(applicationStartup);\n" +
				"\t\treturn this;\n" +
				"\t}\n" +
				"\n" +
				"\t/**\n" +
				"\t * Whether to allow circular references between beans and automatically try to resolve\n" +
				"\t * them.\n" +
				"\t * @param allowCircularReferences whether circular references are allowed\n" +
				"\t * @return the current builder\n" +
				"\t * @since 2.6.0\n" +
				"\t * @see AbstractAutowireCapableBeanFactory#setAllowCircularReferences(boolean)\n" +
				"\t */\n" +
				"\tpublic SpringApplicationBuilder allowCircularReferences(boolean allowCircularReferences) {\n" +
				"\t\tthis.application.setAllowCircularReferences(allowCircularReferences);\n" +
				"\t\treturn this;\n" +
				"\t}\n" +
				"\n" +
				"}"
		);
	}
}