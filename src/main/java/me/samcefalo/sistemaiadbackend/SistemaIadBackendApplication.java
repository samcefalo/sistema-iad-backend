package me.samcefalo.sistemaiadbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SistemaIadBackendApplication {
    
    //TODO Pensar na possibilidade de criar um OUTPOST e INPUT DTO ou manter o mesmo DTO
    //TODO adicionar Placar para jogos
    //TODO adicionar pesquisa para resources
    public static void main(String[] args) {
        SpringApplication.run(SistemaIadBackendApplication.class, args);
    }

}
