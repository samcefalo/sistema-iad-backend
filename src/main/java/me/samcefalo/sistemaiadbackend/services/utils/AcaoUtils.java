package me.samcefalo.sistemaiadbackend.services.utils;

import me.samcefalo.sistemaiadbackend.models.Acao;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class AcaoUtils {

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
