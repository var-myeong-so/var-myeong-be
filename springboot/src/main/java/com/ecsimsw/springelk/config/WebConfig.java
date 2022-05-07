package com.ecsimsw.springelk.config;

import com.ecsimsw.springelk.utils.PaginationArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final PaginationArgumentResolver paginationArgumentResolver;

    public WebConfig(PaginationArgumentResolver paginationArgumentResolver) {
        this.paginationArgumentResolver = paginationArgumentResolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(paginationArgumentResolver);
    }
}
