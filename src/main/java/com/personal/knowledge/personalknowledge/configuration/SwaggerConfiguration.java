package com.personal.knowledge.personalknowledge.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket apiV1() {
        final ApiInfo apiInfo = new ApiInfoBuilder()
            .version("1.0")
            .title("API version 1.0")
            .description("Documentation for API version 1.0")
            .build();

        return new Docket(DocumentationType.SWAGGER_2)
            .groupName("V1")
            .select()
            .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
            .paths(regex("/v1/.*"))
            .build()
            .apiInfo(apiInfo);
    }

    @Bean
    public Docket apiV2() {
        final ApiInfo apiInfo = new ApiInfoBuilder()
            .version("2.0")
            .title("API version 2.0")
            .description("Documentation for API version 2.0")
            .build();

        return new Docket(DocumentationType.SWAGGER_2)
            .groupName("V2")
            .select()
            .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
            .paths(regex("/v2/.*"))
            .build()
            .apiInfo(apiInfo);
    }

}
