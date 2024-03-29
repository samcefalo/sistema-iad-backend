package me.samcefalo.sistemaiadbackend.services;

import me.samcefalo.sistemaiadbackend.estatistica.EstatisticaQuerry;
import me.samcefalo.sistemaiadbackend.models.Acao;
import me.samcefalo.sistemaiadbackend.specifications.criterias.AcaoCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EstatisticaQuerryService {

    @Autowired
    private AcaoService acaoService;
    @Autowired
    private UserSecurityService userSecurityService;

    private final String[] CATEGORIAS = {
            "Desarme",
            "Drible",
            "Finalizacao",
            "Passe",
            "Recepcao"
    };

    public EstatisticaQuerry getQuerryGlobal(AcaoCriteria acaoCriteria) {
        acaoCriteria.setUser(userSecurityService.authenticatedUser().getId());
        List<Acao> acoes = acaoService.findAll(acaoCriteria);

        AcaoCriteria acaoCriteriaGlobal = AcaoCriteria.builder().build();
        return EstatisticaQuerry.builder()
                .acoes(acoes)
                .acoesGlobal(acaoService.findAll(acaoCriteriaGlobal))
                .build();
    }

    public EstatisticaQuerry getQuerryFromEquipe(AcaoCriteria acaoCriteria) {
        acaoCriteria.setUser(userSecurityService.authenticatedUser().getId());
        List<Acao> acoes = acaoService.findAll(acaoCriteria);

        AcaoCriteria acaoCriteriaGlobal = AcaoCriteria.builder()
                .equipe(acaoCriteria.getEquipe()).build();
        return EstatisticaQuerry.builder()
                .acoes(acoes)
                .acoesGlobal(acaoService.findAll(acaoCriteriaGlobal))
                .build();
    }

    public EstatisticaQuerry getQuerryFromJogo(AcaoCriteria acaoCriteria) {
        acaoCriteria.setUser(userSecurityService.authenticatedUser().getId());
        List<Acao> acoes = acaoService.findAll(acaoCriteria);

        AcaoCriteria acaoCriteriaGlobal = AcaoCriteria.builder()
                .jogo(acaoCriteria.getJogo()).build();
        return EstatisticaQuerry.builder()
                .acoes(acoes)
                .acoesGlobal(acaoService.findAll(acaoCriteriaGlobal))
                .build();
    }

    public List<EstatisticaQuerry> getIndividualQuerries(AcaoCriteria acaoCriteria, AcaoCriteria acaoCriteriaGlobal) {
        List<EstatisticaQuerry> estatisticaQuerries = new ArrayList<>();
        acaoCriteria.setUser(userSecurityService.authenticatedUser().getId());
        acaoCriteriaGlobal.setUser(userSecurityService.authenticatedUser().getId());

        for (String categoria : this.CATEGORIAS) {
            acaoCriteria.setCategoria(categoria);
            List<Acao> acoes = acaoService.findAll(acaoCriteria);
            estatisticaQuerries.add(EstatisticaQuerry.builder()
                    .acoes(acoes)
                    .acoesGlobal(acaoService.findAll(acaoCriteriaGlobal))
                    .build());
        }

        return estatisticaQuerries;
    }

}
