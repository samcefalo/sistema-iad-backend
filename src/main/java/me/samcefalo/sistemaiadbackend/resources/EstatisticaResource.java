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
    public ResponseEntity<Estatistica> find(@PathVariable int id,
                                            @RequestParam(value = "categoria", defaultValue = "Acao") String categoria) {
        Estatistica estatistica = estatisticaService.getEstatistica(id, categoria);
        return ResponseEntity.ok().body(estatistica);
    }

}
