package com.whatsapp.assignment.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title(" WhatsApp Assignment")
                        .version("v1.0.0")
                        .description(" is a WhatsApp Assignment for remote work ")
                        .contact(new Contact()
                                .name("WhatsApp Assignment")
                                .email("kindienega10@gmail.com")
                                .url("http://localhost:8088/api/v1/whatsapp/register")));
    }
}
