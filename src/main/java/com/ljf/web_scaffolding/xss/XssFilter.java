package com.ljf.web_scaffolding.xss;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by mr.lin on 2020/7/23
 * https://www.cnblogs.com/dslx/p/11551542.html
 * XSS攻击过滤
 */
@Component
public class XssFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        XssAndSqlHttpServletRequestWrapper xssAndSqlHttpServletRequestWrapper = new XssAndSqlHttpServletRequestWrapper(request);
        chain.doFilter(xssAndSqlHttpServletRequestWrapper,servletResponse);
    }

//    @Bean
//    @Primary 影响全局返回格式，swagger
    public ObjectMapper xssObjectMapper(Jackson2ObjectMapperBuilder builder)
    {
        //解析器
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        //注册xss解析器
        SimpleModule xssModule = new SimpleModule("XssStringJonSerializer");
        xssModule.addSerializer(new XssStringJsonSerializer());
        objectMapper.registerModule(xssModule);
        return objectMapper;
    }

}
