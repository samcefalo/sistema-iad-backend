package me.samcefalo.sistemaiadbackend.resources;

import me.samcefalo.sistemaiadbackend.domain.Tecnico;
import me.samcefalo.sistemaiadbackend.domain.dto.TecnicoDTO;
import me.samcefalo.sistemaiadbackend.mappers.EntidadeMappers;
import me.samcefalo.sistemaiadbackend.services.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {

    @Autowired
    private TecnicoService tecnicoService;
    @Autowired
    private EntidadeMappers mappers;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Tecnico> find(@PathVariable int id) {
        Tecnico tecnico = tecnicoService.find(id);
        return ResponseEntity.ok().body(tecnico);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<TecnicoDTO>> findAll() {
        return ResponseEntity.ok()
                .body(tecnicoService.findAll()
                        .stream().map(tecnico -> mappers.tecnicoToTecnicoDto(tecnico)).collect(Collectors.toList()));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody TecnicoDTO tecnicoDTO) {
        Tecnico tecnico = mappers.tecnicoDtoToTecnico(tecnicoDTO);
        tecnico = tecnicoService.insert(tecnico);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(tecnico.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody TecnicoDTO tecnicoDTO, @PathVariable int id) {
        Tecnico tecnico = mappers.tecnicoDtoToTecnico(tecnicoDTO);
        tecnico.setId(id);
        tecnicoService.update(tecnico);
        return ResponseEntity.noContent().build();
    }

}
