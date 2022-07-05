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
    @Autowired
    private UserSecurityService userSecurityService;

    public EstatisticaQuerry getQuerryGlobal(AcaoCriteria acaoCriteria) {
        acaoCriteria.setUserId(userSecurityService.authenticatedUser().getId());
        List<Acao> acoes = acaoService.findAll(acaoCriteria);

        AcaoCriteria acaoCriteriaGlobal = AcaoCriteria.builder().build();
        return EstatisticaQuerry.builder()
                .acoes(acoes)
                .acoesGlobal(acaoService.findAll(acaoCriteriaGlobal))
                .build();
    }

    public EstatisticaQuerry getQuerryFromEquipe(AcaoCriteria acaoCriteria) {
        acaoCriteria.setUserId(userSecurityService.authenticatedUser().getId());
        List<Acao> acoes = acaoService.findAll(acaoCriteria);

        AcaoCriteria acaoCriteriaGlobal = AcaoCriteria.builder()
                .equipeId(acaoCriteria.getEquipeId()).build();
        return EstatisticaQuerry.builder()
                .acoes(acoes)
                .acoesGlobal(acaoService.findAll(acaoCriteriaGlobal))
                .build();
    }

    public EstatisticaQuerry getQuerryFromJogo(AcaoCriteria acaoCriteria) {
        acaoCriteria.setUserId(userSecurityService.authenticatedUser().getId());
        List<Acao> acoes = acaoService.findAll(acaoCriteria);

        AcaoCriteria acaoCriteriaGlobal = AcaoCriteria.builder()
                .jogoId(acaoCriteria.getJogoId()).build();
        return EstatisticaQuerry.builder()
                .acoes(acoes)
                .acoesGlobal(acaoService.findAll(acaoCriteriaGlobal))
                .build();
    }

}
