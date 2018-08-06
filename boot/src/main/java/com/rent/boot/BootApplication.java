package com.rent.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories("com.rent")
@EntityScan(basePackages = "com.rent.model.entity")
// daca nu-l setez in componentScan si model, nu-l recunoaste si nu se creeaza tabelele, din cauza asta trebuie
// sa-l am pe model ca dependinta
@ComponentScan({"com.rent.model", "com.rent.rest_api", "com.rent.rest_api_impl", "com.rent.service_api", "com.rent.service_api_impl"})
public class BootApplication {
    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args); }
}

