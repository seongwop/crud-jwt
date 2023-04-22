package com.example.crudjwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CrudJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudJwtApplication.class, args);
    }

}
