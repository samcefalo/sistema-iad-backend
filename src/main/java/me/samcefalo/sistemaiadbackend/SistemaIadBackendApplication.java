package me.samcefalo.sistemaiadbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SistemaIadBackendApplication {

    //TODO criar usuario e token de acesso JWT
    //TODO adicionar Placar para jogos
    //TODO adicionar pesquisa para resources
    public static void main(String[] args) {
        SpringApplication.run(SistemaIadBackendApplication.class, args);
    }

}
