package me.samcefalo.sistemaiadbackend.services;

import me.samcefalo.sistemaiadbackend.models.Acao;
import me.samcefalo.sistemaiadbackend.models.Equipe;
import me.samcefalo.sistemaiadbackend.models.Finalizacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstatisticaService {

    //TODO Transformar em interface

    @Autowired
    private JogadorService jogadorService;
    @Autowired
    private JogoService jogoService;

    public int getGols(int jogoId, Equipe equipe) {
        Integer gols = (int) jogadorService.findAcoes(jogoId).stream()
                .filter(acao -> acao instanceof Finalizacao)
                .filter(acao -> acao.isExito() && ((Finalizacao) acao).isGol())
                .count();
        return gols;
    }

    public int getPontuacaoJogador(int jogadorId, Class<? extends Acao> acaoClass) {
        Integer pontuacao = jogadorService.findAcoes(jogadorId).stream()
                .filter(acao -> acao.getClass().isInstance(acaoClass))
                .map(acao -> getPontuacaoAcao(acao))
                .reduce(0, Integer::sum);
        return pontuacao;
    }

    public int getPontuacaoAcao(Acao acao) {
        int pontuacao = 0;

        if (acao.isExito()) {
            pontuacao += acao.getGrauDificuldade();
        } else {
            //TODO corrigir e pensar uma formula para solucionar esse problema (Ex: Acao q tem apenas 2 ou 3 graus)
            pontuacao -= (4 - acao.getGrauDificuldade()) + 1;
        }

        return pontuacao;
    }

}
