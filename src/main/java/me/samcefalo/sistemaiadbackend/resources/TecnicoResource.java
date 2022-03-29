package me.samcefalo.sistemaiadbackend.resources;

import me.samcefalo.sistemaiadbackend.domain.Tecnico;
import me.samcefalo.sistemaiadbackend.domain.dto.TecnicoDTO;
import me.samcefalo.sistemaiadbackend.services.TecnicoService;
import me.samcefalo.sistemaiadbackend.services.mappers.EntidadeMappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {

    @Autowired
    private TecnicoService tecnicoService;
    @Autowired
    private EntidadeMappers mappers;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<TecnicoDTO> find(@PathVariable int id) {
        Tecnico tecnico = tecnicoService.find(id);
        return ResponseEntity.ok().body(mappers.tecnicoToTecnicoDto(tecnico));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<TecnicoDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") int page,
                                                     @RequestParam(value = "linesPerPage", defaultValue = "24") int linesPerPage,
                                                     @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
                                                     @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        return ResponseEntity.ok()
                .body(tecnicoService.findPage(page, linesPerPage, orderBy, direction)
                        .map(tecnico -> mappers.tecnicoToTecnicoDto(tecnico)));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody TecnicoDTO tecnicoDTO) {
        Tecnico tecnico = mappers.tecnicoDtoToTecnico(tecnicoDTO);
        tecnico = tecnicoService.insert(tecnico);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(tecnico.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody TecnicoDTO tecnicoDTO, @PathVariable int id) {
        Tecnico tecnico = mappers.tecnicoDtoToTecnico(tecnicoDTO);
        tecnico.setId(id);
        tecnicoService.update(tecnico);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable int id) {
        tecnicoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
