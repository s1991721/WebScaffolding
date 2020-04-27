package com.ljf.web_scaffolding.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by mr.lin on 2020/4/27
 * swagger2配置
 */
@EnableSwagger2
@Configuration
public class Swagger2Configuration {

    @Bean
    public Docket docketOne() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("分组名1")
                .select()  //这里已转换成了ApiSelectorBuilder
                .apis(RequestHandlerSelectors.basePackage("com.ljf.web_scaffolding.controllers"))
                .build();
    }

    @Bean
    public Docket docketTwo() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("分组名2")
                .select()  //这里已转换成了ApiSelectorBuilder
                .apis(RequestHandlerSelectors.basePackage("com.ljf.web_scaffolding.controllers"))
                .paths(PathSelectors.any()) //控制路径下哪些能够显示
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("文档标题")
                .description("文档描述")
                .version("v1.0")
                .license("简书")
                .licenseUrl("https://www.jianshu.com/u/0790f0629fc6")
                .contact(new Contact("ljf", "https://www.baidu.com", "@qq.com"))
                .termsOfServiceUrl("termsOfServiceUrl")
                .build();
    }

}
