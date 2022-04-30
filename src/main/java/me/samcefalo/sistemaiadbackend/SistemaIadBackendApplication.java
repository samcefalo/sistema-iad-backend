package me.samcefalo.sistemaiadbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SistemaIadBackendApplication {


    public static void main(String[] args) {
        SpringApplication.run(SistemaIadBackendApplication.class, args);
    }

}
