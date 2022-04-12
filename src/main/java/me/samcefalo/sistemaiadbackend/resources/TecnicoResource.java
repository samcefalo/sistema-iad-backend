package me.samcefalo.sistemaiadbackend.resources;

import me.samcefalo.sistemaiadbackend.dtos.TecnicoDTO;
import me.samcefalo.sistemaiadbackend.dtos.UserDTO;
import me.samcefalo.sistemaiadbackend.mappers.EntidadeMapper;
import me.samcefalo.sistemaiadbackend.models.Tecnico;
import me.samcefalo.sistemaiadbackend.services.TecnicoService;
import me.samcefalo.sistemaiadbackend.services.UserSecurityService;
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
    private EntidadeMapper entidadeMapper;
    @Autowired
    private UserSecurityService userSecurityService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<TecnicoDTO> find(@PathVariable int id) {
        Tecnico tecnico = tecnicoService.find(id);
        return ResponseEntity.ok().body((TecnicoDTO) entidadeMapper.mapToDTO(tecnico, TecnicoDTO.class));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<TecnicoDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") int page,
                                                     @RequestParam(value = "linesPerPage", defaultValue = "24") int linesPerPage,
                                                     @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
                                                     @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        return ResponseEntity.ok()
                .body(tecnicoService.findPage(page, linesPerPage, orderBy, direction)
                        .map(tecnico -> (TecnicoDTO) entidadeMapper.mapToDTO(tecnico, TecnicoDTO.class)));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody TecnicoDTO tecnicoDTO) {
        if (userSecurityService.authenticated() != null)
            tecnicoDTO.setUser(UserDTO.builder().id(userSecurityService.authenticated().getId()).build());
        Tecnico tecnico = (Tecnico) entidadeMapper.mapToModel(tecnicoDTO, Tecnico.class);
        tecnico = tecnicoService.insert(tecnico);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(tecnico.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody TecnicoDTO tecnicoDTO, @PathVariable int id) {
        Tecnico tecnico = (Tecnico) entidadeMapper.mapToModel(tecnicoDTO, Tecnico.class);
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
