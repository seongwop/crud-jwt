package com.example.jwtpost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class JwtpostApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtpostApplication.class, args);
    }

}
