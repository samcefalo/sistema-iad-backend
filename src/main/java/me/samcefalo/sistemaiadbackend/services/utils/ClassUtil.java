package me.samcefalo.sistemaiadbackend.services.utils;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class ClassUtil {

    public Class<?> getJogoClass(String categoria) throws ClassNotFoundException {
        categoria = categoria.toLowerCase();
        categoria = StringUtils.capitalize(categoria);
        return Class.forName("me.samcefalo.sistemaiadbackend.models.Jogo" + categoria);
    }
}
