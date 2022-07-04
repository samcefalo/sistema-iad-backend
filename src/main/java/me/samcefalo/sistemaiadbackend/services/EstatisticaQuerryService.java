package me.samcefalo.sistemaiadbackend.services;

import me.samcefalo.sistemaiadbackend.estatistica.EstatisticaQuerry;
import me.samcefalo.sistemaiadbackend.models.Acao;
import me.samcefalo.sistemaiadbackend.specifications.criterias.AcaoCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstatisticaQuerryService {

    @Autowired
    private AcaoService acaoService;

    public EstatisticaQuerry getQuerryGlobal(String categoria) {
        AcaoCriteria acaoCriteria = AcaoCriteria.builder()
                .categoria(categoria).build();
        List<Acao> acoes = acaoService.findAll(acaoCriteria);

        AcaoCriteria acaoCriteriaGlobal = AcaoCriteria.builder().build();
        return EstatisticaQuerry.builder()
                .acoes(acoes)
                .acoesGlobal(acaoService.findAll(acaoCriteriaGlobal))
                .build();
    }

    public EstatisticaQuerry getQuerryFromJogador(int atletaId, int equipeId, String categoria) {
        AcaoCriteria acaoCriteria = AcaoCriteria.builder()
                .categoria(categoria)
                .atletaId(atletaId).build();

        List<Acao> acoes = acaoService.findAll(acaoCriteria);

        AcaoCriteria acaoCriteriaGlobal = AcaoCriteria.builder()
                .categoria(categoria).equipeId(equipeId).build();

        return EstatisticaQuerry.builder()
                .acoes(acoes)
                .acoesGlobal(acaoService.findAll(acaoCriteriaGlobal))
                .build();
    }

    public EstatisticaQuerry getQuerryFromEquipe(int equipeId, String categoria) {
        AcaoCriteria acaoCriteria = AcaoCriteria.builder()
                .categoria(categoria)
                .equipeId(equipeId).build();
        List<Acao> acoes = acaoService.findAll(acaoCriteria);

        AcaoCriteria acaoCriteriaGlobal = AcaoCriteria.builder()
                .categoria(categoria).build();
        return EstatisticaQuerry.builder()
                .acoes(acoes)
                .acoesGlobal(acaoService.findAll(acaoCriteriaGlobal))
                .build();
    }

    public EstatisticaQuerry getQuerryFromJogo(int jogoId, String categoria) {
        AcaoCriteria acaoCriteria = AcaoCriteria.builder()
                .categoria(categoria)
                .jogoId(jogoId).build();
        List<Acao> acoes = acaoService.findAll(acaoCriteria);

        AcaoCriteria acaoCriteriaGlobal = AcaoCriteria.builder()
                .categoria(categoria).build();
        return EstatisticaQuerry.builder()
                .acoes(acoes)
                .acoesGlobal(acaoService.findAll(acaoCriteriaGlobal))
                .build();
    }

}
