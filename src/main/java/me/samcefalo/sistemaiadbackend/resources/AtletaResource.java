package me.samcefalo.sistemaiadbackend.resources;

import me.samcefalo.sistemaiadbackend.dtos.AtletaDTO;
import me.samcefalo.sistemaiadbackend.dtos.UserDTO;
import me.samcefalo.sistemaiadbackend.mappers.AtletaMapper;
import me.samcefalo.sistemaiadbackend.models.Atleta;
import me.samcefalo.sistemaiadbackend.services.AtletaService;
import me.samcefalo.sistemaiadbackend.services.UserSecurityService;
import me.samcefalo.sistemaiadbackend.specifications.criterias.AtletaCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/atletas")
public class AtletaResource {

    @Autowired
    private AtletaService atletaService;
    @Autowired
    private AtletaMapper atletaMapper;
    @Autowired
    private UserSecurityService userSecurityService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<AtletaDTO> find(@PathVariable int id) {
        Atleta atleta = atletaService.find(id);
        return ResponseEntity.ok().body(atletaMapper.mapToDTO(atleta, AtletaDTO.class));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<AtletaDTO>> findPage(@Valid AtletaCriteria atletaCriteria,
                                                    Pageable pageable) {
        return ResponseEntity.ok()
                .body(atletaService.findAllPage(atletaCriteria, pageable)
                        .map(atleta -> atletaMapper.mapToDTO(atleta, AtletaDTO.class)));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody AtletaDTO atletaDTO) {
        atletaDTO.setUser(UserDTO.builder().id(userSecurityService.authenticated().getId()).build());
        Atleta atleta = atletaMapper.mapToModel(atletaDTO, Atleta.class);
        atleta = atletaService.insert(atleta);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(atleta.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody AtletaDTO atletaDTO, @PathVariable int id) {
        Atleta atleta = (Atleta) atletaMapper.mapToModel(atletaDTO, Atleta.class);
        atleta.setId(id);
        atletaService.update(atleta);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable int id) {
        atletaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
