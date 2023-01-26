package com.ftn.e2.isa.blood_simple.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

/*
konfiguracija omogucava generisanje metapodataka API-ju, poput vlasnika, uslova koriscenja, licence i sl.
moguce je konfigurisati i vise servera
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openApi() {
        ArrayList<Server> servers = new ArrayList<>(3);
        servers.add(new Server().url("http://localhost:8080/").description("development server"));
        servers.add(new Server().url("http://qa:8081/").description("test server"));
        servers.add(new Server().url("http://www.rest-example.com/").description("production server"));

        return new OpenAPI()
                .info(new Info()
                        .title("Blood Simple")
                        .description("Blood Simple project with OpenAPI documentation. It's a simple project for blood donation.")
                        .version("v1.0")
                        .contact(new Contact()      // kontakt informacije
                                .name("Laslo Uri")
                                .url("https://github.com/laslo-uri")     // mora biti u url formatu
                                .email("lacikauri@gmail.com"))
                        .termsOfService("TOC")
                        .license(new License().name("License").url("#"))
                )
                .servers(servers);
    }

}
