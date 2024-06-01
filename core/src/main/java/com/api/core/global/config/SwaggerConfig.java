package com.api.core.global.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {
    private final String JWT = "JWT";
    private final String BEARER = "bearer";
    private final String AUTHORIZATION = "Authorization";
    private final String ACCESS_TOKEN = "access_token";


    @Bean
    public OpenAPI openAPI() {
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(JWT);
        return new OpenAPI()
                .servers(getServers())
                .info(getInfo())
                .addSecurityItem(securityRequirement)
                .components(getComponents());
    }

    private List<Server> getServers() {
        return List.of(new Server()
                .url("/")
                .description("백엔드 api 서버")
        );
    }

    private Info getInfo() {
        return new Info()
                .title("DDORING API")
                .description("DDORING API 명세서입니다.")
                .version("demo");
    }

    private Components getComponents() {
        return new Components().addSecuritySchemes(ACCESS_TOKEN, new SecurityScheme()
                .name(AUTHORIZATION)
                .in(SecurityScheme.In.HEADER)
                .type(SecurityScheme.Type.HTTP)
                .scheme(BEARER)
                .bearerFormat(JWT)
        );
    }
}
