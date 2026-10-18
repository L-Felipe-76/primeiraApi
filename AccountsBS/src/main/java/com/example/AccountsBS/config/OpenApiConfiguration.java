package com.example.AccountsBS.config;

//Spring Framework
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Dependencies
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI openAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("API: Bairro Seguro - Cadastramentos")
                        .description("API para cadastro e gerenciamento de contas, moradores associados, ocorrências relatadas e redes de apoio criadas")
                        .version("Alfa: 1.0"))
                .components(new Components()
                        .addSecuritySchemes(
                                "bearerAuth",
                                new SecurityScheme()
                                    .type(SecurityScheme.Type.HTTP)
                                    .scheme("bearer")
                                    .bearerFormat("JWT")
                                    .scheme("bearer")
                                    .bearerFormat("JWT")
                        ));
    }
}
