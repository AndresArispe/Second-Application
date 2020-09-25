package com.milankas.training.companyapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CompanyApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompanyApiApplication.class, args);
    }

}
