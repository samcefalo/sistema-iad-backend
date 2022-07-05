package me.samcefalo.sistemaiadbackend.resources;

import me.samcefalo.sistemaiadbackend.estatistica.Estatistica;
import me.samcefalo.sistemaiadbackend.services.EstatisticaQuerryService;
import me.samcefalo.sistemaiadbackend.services.EstatisticaService;
import me.samcefalo.sistemaiadbackend.specifications.criterias.AcaoCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/estatisticas")
public class EstatisticaResource {

    @Autowired
    private EstatisticaQuerryService querryService;
    @Autowired
    private EstatisticaService estatisticaService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Estatistica> find(@RequestParam(value = "placar", required = false) String placar,
                                            @RequestParam(value = "categoria", required = false) String categoria,
                                            @RequestParam(value = "grauDificuldade", defaultValue = "0") Integer grauDificuldade,
                                            @RequestParam(value = "esporte", defaultValue = "0") Integer esporte,
                                            @RequestParam(value = "tipoJogo", defaultValue = "0") Integer tipoJogo,
                                            @RequestParam(value = "area", defaultValue = "0") Integer area,
                                            @RequestParam(value = "etapa", defaultValue = "0") Integer etapa,
                                            @RequestParam(value = "tempo", required = false) Integer tempo,
                                            @RequestParam(value = "jogo", required = false) Integer jogo,
                                            @RequestParam(value = "equipe", required = false) Integer equipe,
                                            @RequestParam(value = "atleta", required = false) Integer atleta,
                                            @RequestParam(value = "exito", required = false) Boolean exito,
                                            @RequestParam(value = "gol", required = false) Boolean gol,
                                            @RequestParam(value = "posseDeBola", required = false) Boolean posseDeBola) {

        AcaoCriteria acaoCriteria = AcaoCriteria.builder()
                .placar(placar).categoria(categoria)
                .grauDificuldade(grauDificuldade).area(area)
                .tempo(tempo).etapa(etapa)
                .jogoId(jogo).equipeId(equipe)
                .atletaId(atleta).exito(exito)
                .gol(gol).posseDeBola(posseDeBola)
                .tipoJogo(tipoJogo).esporte(esporte)
                .build();

        Estatistica estatistica = estatisticaService
                .getEstatistica(querryService.getQuerryGlobal(acaoCriteria));
        return ResponseEntity.ok().body(estatistica);
    }

    @RequestMapping(value = "/equipe", method = RequestMethod.GET)
    public ResponseEntity<Estatistica> findFromEquipe(@RequestParam(value = "placar", required = false) String placar,
                                                      @RequestParam(value = "categoria", required = false) String categoria,
                                                      @RequestParam(value = "grauDificuldade", defaultValue = "0") Integer grauDificuldade,
                                                      @RequestParam(value = "esporte", defaultValue = "0") Integer esporte,
                                                      @RequestParam(value = "tipoJogo", defaultValue = "0") Integer tipoJogo,
                                                      @RequestParam(value = "area", defaultValue = "0") Integer area,
                                                      @RequestParam(value = "etapa", defaultValue = "0") Integer etapa,
                                                      @RequestParam(value = "tempo", required = false) Integer tempo,
                                                      @RequestParam(value = "jogo", required = false) Integer jogo,
                                                      @RequestParam(value = "equipe", required = false) Integer equipe,
                                                      @RequestParam(value = "atleta", required = false) Integer atleta,
                                                      @RequestParam(value = "exito", required = false) Boolean exito,
                                                      @RequestParam(value = "gol", required = false) Boolean gol,
                                                      @RequestParam(value = "posseDeBola", required = false) Boolean posseDeBola) {

        AcaoCriteria acaoCriteria = AcaoCriteria.builder()
                .placar(placar).categoria(categoria)
                .grauDificuldade(grauDificuldade).area(area)
                .tempo(tempo).etapa(etapa)
                .jogoId(jogo).equipeId(equipe)
                .atletaId(atleta).exito(exito)
                .gol(gol).posseDeBola(posseDeBola)
                .tipoJogo(tipoJogo).esporte(esporte)
                .build();

        Estatistica estatistica = estatisticaService
                .getEstatistica(querryService.getQuerryFromEquipe(acaoCriteria));
        return ResponseEntity.ok().body(estatistica);
    }

    @RequestMapping(value = "/jogo", method = RequestMethod.GET)
    public ResponseEntity<Estatistica> findFromJogo(@RequestParam(value = "placar", required = false) String placar,
                                                    @RequestParam(value = "categoria", required = false) String categoria,
                                                    @RequestParam(value = "grauDificuldade", defaultValue = "0") Integer grauDificuldade,
                                                    @RequestParam(value = "esporte", defaultValue = "0") Integer esporte,
                                                    @RequestParam(value = "tipoJogo", defaultValue = "0") Integer tipoJogo,
                                                    @RequestParam(value = "area", defaultValue = "0") Integer area,
                                                    @RequestParam(value = "etapa", defaultValue = "0") Integer etapa,
                                                    @RequestParam(value = "tempo", required = false) Integer tempo,
                                                    @RequestParam(value = "jogo", required = false) Integer jogo,
                                                    @RequestParam(value = "equipe", required = false) Integer equipe,
                                                    @RequestParam(value = "atleta", required = false) Integer atleta,
                                                    @RequestParam(value = "exito", required = false) Boolean exito,
                                                    @RequestParam(value = "gol", required = false) Boolean gol,
                                                    @RequestParam(value = "posseDeBola", required = false) Boolean posseDeBola) {

        AcaoCriteria acaoCriteria = AcaoCriteria.builder()
                .placar(placar).categoria(categoria)
                .grauDificuldade(grauDificuldade).area(area)
                .tempo(tempo).etapa(etapa)
                .jogoId(jogo).equipeId(equipe)
                .atletaId(atleta).exito(exito)
                .gol(gol).posseDeBola(posseDeBola)
                .tipoJogo(tipoJogo).esporte(esporte)
                .build();
        Estatistica estatistica = estatisticaService
                .getEstatistica(querryService.getQuerryFromJogo(acaoCriteria));
        return ResponseEntity.ok().body(estatistica);
    }

}
