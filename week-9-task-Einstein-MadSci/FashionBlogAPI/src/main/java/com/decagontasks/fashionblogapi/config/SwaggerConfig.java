package com.decagontasks.fashionblogapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Value("${application.version}")
    private String version;

    @Bean
    public OpenAPI api(){
        return new OpenAPI()
                .info(new Info()
                        .title("Fashion Blog API")
                        .description("Api for our fashion blog")
                        .version(version));
    }

    @Bean
    public GroupedOpenApi usersEndpoints(){
        return GroupedOpenApi
                .builder()
                .group("Users")
                .pathsToMatch("/user/**")
                .build();
    }

    @Bean
    public GroupedOpenApi adminEndpoints(){
        return GroupedOpenApi
                .builder()
                .group("Admin")
                .pathsToMatch("/admin/**")
                .build();
    }

    @Bean
    public GroupedOpenApi postEndpoints(){
        return GroupedOpenApi
                .builder()
                .group("Post")
                .pathsToMatch("/posts/**")
                .build();
    }
}