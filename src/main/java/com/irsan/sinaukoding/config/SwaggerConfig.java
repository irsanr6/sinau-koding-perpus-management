package com.irsan.sinaukoding.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket apiConfig() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder().title("Sinau Koding Perpus Management").build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.irsan.sinaukoding.controller"))
                .paths(PathSelectors.regex("/api/v1/.*"))
                .build()
                .ignoredParameterTypes(Pageable.class, Sort.class);
    }

}
