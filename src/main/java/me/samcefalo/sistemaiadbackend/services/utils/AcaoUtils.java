package me.samcefalo.sistemaiadbackend.services.utils;

import me.samcefalo.sistemaiadbackend.models.Acao;
import me.samcefalo.sistemaiadbackend.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class AcaoUtils {

    @Autowired
    private ClassUtils classUtil;

    public Class<?> getClass(String categoria) {
        Class<?> categoriaClass;
        try {
            categoriaClass = classUtil.getAcaoClass(categoria);
        } catch (ClassNotFoundException e) {
            throw new ObjectNotFoundException("Acao não encontrada. ID: " + categoria + ", Tipo: " + Acao.class.getSimpleName());
        }
        return categoriaClass;
    }

    public List<Acao> getOnlyExito(List<Acao> acoes) {
        Stream<Acao> acaoStream = acoes.stream();
        return acaoStream
                .filter(acao -> acao.isExito())
                .collect(Collectors.toList());
    }

    public List<Integer> parseAcaoToInteger(List<Acao> acoes) {
        Stream<Acao> acaoStream = acoes.stream();
        return acaoStream
                .mapToInt(Acao::getPontuacao)
                .boxed()
                .collect(Collectors.toList());
    }
}
