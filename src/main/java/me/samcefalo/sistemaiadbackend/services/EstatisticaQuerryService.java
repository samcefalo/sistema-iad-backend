package me.samcefalo.sistemaiadbackend.services;

import me.samcefalo.sistemaiadbackend.estatistica.EstatisticaQuerry;
import me.samcefalo.sistemaiadbackend.models.Acao;
import me.samcefalo.sistemaiadbackend.services.utils.AcaoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstatisticaQuerryService {

    @Autowired
    private AcaoService acaoService;
    @Autowired
    private AtletaService atletaService;
    @Autowired
    private EquipeService equipeService;
    @Autowired
    private JogoService jogoService;
    @Autowired
    private AcaoUtils acaoUtils;

    public EstatisticaQuerry getQuerryGlobal(String categoria) {
        Class<?> categoriaClass = acaoUtils.getClass(categoria);
        List<Acao> acoes = acaoService.findAll(categoriaClass);

        return EstatisticaQuerry.builder()
                .acoes(acoes)
                .acoesGlobal(acaoUtils.getAcoesGlobal(categoriaClass))
                .build();
    }

    public EstatisticaQuerry getQuerryFromJogador(int atletaId, int equipeId, String categoria) {
        Class<?> categoriaClass = acaoUtils.getClass(categoria);
        List<Acao> acoes = atletaService.findAcoes(atletaId, categoriaClass);

        return EstatisticaQuerry.builder()
                .acoes(acoes)
                .acoesGlobal(acaoUtils.getAcoesFromEquipe(equipeId, categoriaClass))
                .build();
    }

    public EstatisticaQuerry getQuerryFromEquipe(int equipeId, String categoria) {
        Class<?> categoriaClass = acaoUtils.getClass(categoria);
        List<Acao> acoes = equipeService.findAcoes(equipeId, categoriaClass);

        return EstatisticaQuerry.builder()
                .acoes(acoes)
                .acoesGlobal(acaoUtils.getAcoesGlobal(categoriaClass))
                .build();
    }

    public EstatisticaQuerry getQuerryFromJogo(int jogoId, String categoria) {
        Class<?> categoriaClass = acaoUtils.getClass(categoria);
        List<Acao> acoes = jogoService.findAcoes(jogoId, categoriaClass);

        return EstatisticaQuerry.builder()
                .acoes(acoes)
                .acoesGlobal(acaoUtils.getAcoesGlobal(categoriaClass))
                .build();
    }

}
