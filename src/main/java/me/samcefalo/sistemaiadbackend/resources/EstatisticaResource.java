package me.samcefalo.sistemaiadbackend.resources;

import me.samcefalo.sistemaiadbackend.estatistica.Estatistica;
import me.samcefalo.sistemaiadbackend.services.EstatisticaQuerryService;
import me.samcefalo.sistemaiadbackend.services.EstatisticaService;
import me.samcefalo.sistemaiadbackend.specifications.criterias.AcaoCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/estatisticas")
public class EstatisticaResource {

    @Autowired
    private EstatisticaQuerryService querryService;
    @Autowired
    private EstatisticaService estatisticaService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Estatistica> find(@Valid AcaoCriteria acaoCriteria) {

        Estatistica estatistica = estatisticaService
                .getEstatistica(querryService.getQuerryGlobal(acaoCriteria));
        return ResponseEntity.ok().body(estatistica);
    }

    @RequestMapping(value = "/equipe", method = RequestMethod.GET)
    public ResponseEntity<Estatistica> findFromEquipe(@Valid AcaoCriteria acaoCriteria) {

        Estatistica estatistica = estatisticaService
                .getEstatistica(querryService.getQuerryFromEquipe(acaoCriteria));
        return ResponseEntity.ok().body(estatistica);
    }

    @RequestMapping(value = "/jogo", method = RequestMethod.GET)
    public ResponseEntity<Estatistica> findFromJogo(@Valid AcaoCriteria acaoCriteria) {

        Estatistica estatistica = estatisticaService
                .getEstatistica(querryService.getQuerryFromJogo(acaoCriteria));
        return ResponseEntity.ok().body(estatistica);
    }

    @RequestMapping(value = "/byCategoria", method = RequestMethod.GET)
    public ResponseEntity<List<Estatistica>> findEachCategoria(@Valid AcaoCriteria acaoCriteria) {

        AcaoCriteria acaoCriteriaGlobal = AcaoCriteria.builder()
                .equipe(acaoCriteria.getEquipe()).jogo(acaoCriteria.getJogo()).build();

        List<Estatistica> estatisticas = new ArrayList<>();
        estatisticas.addAll(querryService.getIndividualQuerries(acaoCriteria, acaoCriteriaGlobal).stream()
                .map(estatisticaQuerry -> estatisticaService.getEstatistica(estatisticaQuerry))
                .collect(Collectors.toList()));

        return ResponseEntity.ok().body(estatisticas);
    }

}
