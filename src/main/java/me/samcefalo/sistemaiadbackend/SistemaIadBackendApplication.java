package me.samcefalo.sistemaiadbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SistemaIadBackendApplication {


    //TODO resolver UsernameNotFoundException: post /login
    //TODO criar usuario e token de acesso JWT
    public static void main(String[] args) {
        SpringApplication.run(SistemaIadBackendApplication.class, args);
    }

}
