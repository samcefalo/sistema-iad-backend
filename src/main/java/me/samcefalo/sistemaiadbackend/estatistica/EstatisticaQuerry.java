package me.samcefalo.sistemaiadbackend.estatistica;

import lombok.Builder;
import lombok.Data;
import me.samcefalo.sistemaiadbackend.models.Acao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder
public class EstatisticaQuerry {

    private List<Acao> acoes = new ArrayList<>();
    private List<Acao> acoesGlobal = new ArrayList<>();

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
