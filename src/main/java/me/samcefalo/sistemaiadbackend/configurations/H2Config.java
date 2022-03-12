package me.samcefalo.sistemaiadbackend.configurations;

import me.samcefalo.sistemaiadbackend.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class H2Config {

    @Autowired
    private DBService dbService;

    @Bean
    public boolean startDatabase() {
        dbService.startTestDatas();
        return true;
    }

}