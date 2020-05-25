package com.bren.carshop.config;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    @Bean
    public Docket swaggerSpringfoxDocket() {

        Docket docket =
                new Docket(DocumentationType.SWAGGER_2)
                        .useDefaultResponseMessages(false)
                        .pathMapping("/")
                        .apiInfo(ApiInfo.DEFAULT)
                        .forCodeGeneration(true)
                        .genericModelSubstitutes(ResponseEntity.class)
                        .ignoredParameterTypes(Pageable.class)
                        .ignoredParameterTypes(java.sql.Date.class)
                        .directModelSubstitute(java.time.LocalDate.class, java.sql.Date.class)
                        .directModelSubstitute(java.time.ZonedDateTime.class, Date.class)
                        .directModelSubstitute(java.time.LocalDateTime.class, Date.class)
                        .securityContexts(Lists.newArrayList(securityContext()))
                        .securitySchemes(Lists.newArrayList(apiKey()))
                        .useDefaultResponseMessages(false);


        docket = docket.select().apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).build();
        return docket;

    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(this::include)
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope =
                new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(new SecurityReference("JWT", authorizationScopes));
    }

    private boolean include(String path) {
        return !path.startsWith("/ownSecurity");
    }
}
