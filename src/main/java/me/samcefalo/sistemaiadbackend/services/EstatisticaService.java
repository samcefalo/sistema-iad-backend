package me.samcefalo.sistemaiadbackend.services;

import me.samcefalo.sistemaiadbackend.models.Acao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstatisticaService {

    @Autowired
    private JogadorService jogadorService;

    public int getPontuacao(int jogadorId, Class<? extends Acao> acaoClass) {
        Integer pontuacao = jogadorService.findAcoes(jogadorId).stream()
                .filter(acao -> acao.getClass().isInstance(acaoClass))
                .map(acao -> acao.getPontuacao())
                .reduce(0, Integer::sum);
        return pontuacao;
    }

}
