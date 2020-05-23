package com.ljf.web_scaffolding.config;

import com.ljf.web_scaffolding.interceptor.CostInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by mr.lin on 2020/5/23
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    CostInterceptor costInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(costInterceptor);
    }
}
