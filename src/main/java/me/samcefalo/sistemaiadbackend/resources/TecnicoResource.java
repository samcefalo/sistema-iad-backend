package me.samcefalo.sistemaiadbackend.resources;

import me.samcefalo.sistemaiadbackend.domain.Tecnico;
import me.samcefalo.sistemaiadbackend.services.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {

    @Autowired
    private TecnicoService tecnicoService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Tecnico> find(@PathVariable int id) {
        Tecnico tecnico = tecnicoService.find(id);
        return ResponseEntity.ok().body(tecnico);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Tecnico>> findAll() {
        return ResponseEntity.ok()
                .body(tecnicoService.findAll());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody Tecnico tecnico) {
        tecnico = tecnicoService.insert(tecnico);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(tecnico.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody Tecnico tecnico, @PathVariable int id) {
        tecnico.setId(id);
        tecnicoService.update(tecnico);
        return ResponseEntity.noContent().build();
    }

}
