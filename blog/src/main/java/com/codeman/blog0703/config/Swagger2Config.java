package com.codeman.blog0703.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author: zhanghongjie
 * @description:
 * @date: 2021/5/29 16:02
 * @version: 1.0
 */
@EnableSwagger2
@Configuration
public class Swagger2Config {


    /*@Bean
    public Docket groupB() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("B");
    }*/

    @Bean
    public Docket webApiConfig(Environment environment){
        Profiles profiles = Profiles.of("test", "dev");

        boolean enable = environment.acceptsProfiles(profiles);
        enable = true;
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("")
                .apiInfo(webApiInfo())
                .enable(enable)
                .groupName("Blog")
                .select()
                // 扫描指定包
//                .apis(RequestHandlerSelectors.basePackage("com.codeman.mall4springcloud.controller"))
//                .apis(RequestHandlerSelectors.basePackage("com.codeman.mall4springcloud.entity"))
                .paths(Predicates.not(PathSelectors.regex("/admin/.*")))
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build();

    }

    private ApiInfo webApiInfo(){

        return new ApiInfoBuilder()
                .title("blog0703的API文档")
                .description("本文档描述了blog微服务接口定义")
                .version("1.0")
                .contact(new Contact("java", "http://codeman.com", "88888888@qq.com"))
                .build();
    }

}
