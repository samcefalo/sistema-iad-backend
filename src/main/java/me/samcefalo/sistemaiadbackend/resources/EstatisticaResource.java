package me.samcefalo.sistemaiadbackend.resources;

import me.samcefalo.sistemaiadbackend.estatistica.Estatistica;
import me.samcefalo.sistemaiadbackend.services.EstatisticaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/estatisticas")
public class EstatisticaResource {

    @Autowired
    private EstatisticaService estatisticaService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Estatistica> find(@RequestParam(value = "categoria", defaultValue = "Acao") String categoria) {
        Estatistica estatistica = estatisticaService.getEstatistica(categoria);
        return ResponseEntity.ok().body(estatistica);
    }

    @RequestMapping(value = "/atleta/{id}", method = RequestMethod.GET)
    public ResponseEntity<Estatistica> findAtleta(@PathVariable int id,
                                                  @RequestParam(value = "categoria", defaultValue = "Acao") String categoria) {
        Estatistica estatistica = estatisticaService.getEstatisticaFromJogador(id, categoria);
        return ResponseEntity.ok().body(estatistica);
    }

    @RequestMapping(value = "/equipe/{id}", method = RequestMethod.GET)
    public ResponseEntity<Estatistica> findEquipe(@PathVariable int id,
                                                  @RequestParam(value = "categoria", defaultValue = "Acao") String categoria) {
        Estatistica estatistica = estatisticaService.getEstatisticaFromEquipe(id, categoria);
        return ResponseEntity.ok().body(estatistica);
    }

    @RequestMapping(value = "/jogo/{id}", method = RequestMethod.GET)
    public ResponseEntity<Estatistica> findJogo(@PathVariable int id,
                                                @RequestParam(value = "categoria", defaultValue = "Acao") String categoria) {
        Estatistica estatistica = estatisticaService.getEstatisticaFromJogo(id, categoria);
        return ResponseEntity.ok().body(estatistica);
    }

}
