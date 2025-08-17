package com.devotion.ztita.utils;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        final String securityScheme = "bearerAuth";

    return new OpenAPI()
            .info(new Info()
                    .title("E-Commerce API")
                    .version("1.0")
                    .description("Documentación de la API con autenticación JWT"))
            .addSecurityItem(new SecurityRequirement().addList(securityScheme))
            .components(new Components().addSecuritySchemes(securityScheme,new SecurityScheme()
                    .name(securityScheme)
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT")
            ));
    }
}
