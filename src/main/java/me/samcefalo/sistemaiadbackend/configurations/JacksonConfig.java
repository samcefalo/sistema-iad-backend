package me.samcefalo.sistemaiadbackend.configurations;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import me.samcefalo.sistemaiadbackend.services.utils.CustomLocalDateDeserializer;
import me.samcefalo.sistemaiadbackend.services.utils.CustomLocalDateSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class JacksonConfig {

    @Bean
    public Module customModule() {
        SimpleModule module = new SimpleModule();
        module.addSerializer(LocalDate.class, new CustomLocalDateSerializer());
        module.addDeserializer(LocalDate.class, new CustomLocalDateDeserializer());
        return module;
    }
}
