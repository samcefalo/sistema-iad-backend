package me.samcefalo.sistemaiadbackend.services.utils;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class ClassUtils {

    public Class<?> getJogoClass(String categoria) throws ClassNotFoundException {
        categoria = StringUtils.capitalize(categoria.toLowerCase());
        return Class.forName("me.samcefalo.sistemaiadbackend.models.Jogo" + categoria);
    }

    public Class<?> getAcaoClass(String categoria) throws ClassNotFoundException {
        categoria = StringUtils.capitalize(categoria.toLowerCase());
        return Class.forName("me.samcefalo.sistemaiadbackend.models." + categoria);
    }
}
