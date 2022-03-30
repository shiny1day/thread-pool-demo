package com.shiny.demo.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author dengkongze
 * @email kongze.deng@xintech.cn
 * @date 2022/3/30 15:43
 * @desc 添加拦截器
 */
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    @Bean
    public MDCHandlerInterceptor mdcHandlerInterceptor() {
        return new MDCHandlerInterceptor();
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(mdcHandlerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/swagger-ui.html");
        super.addInterceptors(registry);
    }

}
