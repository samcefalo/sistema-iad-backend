package me.samcefalo.sistemaiadbackend.resources;

import me.samcefalo.sistemaiadbackend.dtos.EquipeDTO;
import me.samcefalo.sistemaiadbackend.dtos.UserDTO;
import me.samcefalo.sistemaiadbackend.mappers.EquipeMapper;
import me.samcefalo.sistemaiadbackend.models.Equipe;
import me.samcefalo.sistemaiadbackend.services.EquipeService;
import me.samcefalo.sistemaiadbackend.services.UserSecurityService;
import me.samcefalo.sistemaiadbackend.specifications.criterias.EquipeCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/equipes")
public class EquipeResource {

    @Autowired
    private UserSecurityService userSecurityService;
    @Autowired
    private EquipeService equipeService;
    @Autowired
    private EquipeMapper equipeMapper;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<EquipeDTO> find(@PathVariable int id) {
        Equipe equipe = equipeService.find(id);
        return ResponseEntity.ok().body(equipeMapper.mapToDTO(equipe, EquipeDTO.class));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<EquipeDTO>> findPage(@RequestParam(value = "nome", required = false) String nome,
                                                    @RequestParam(value = "jogo", required = false) Integer jogo,
                                                    @RequestParam(value = "atleta", required = false) Integer atleta,
                                                    @RequestParam(value = "page", defaultValue = "0") int page,
                                                    @RequestParam(value = "linesPerPage", defaultValue = "24") int linesPerPage,
                                                    @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
                                                    @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        EquipeCriteria equipeCriteria = EquipeCriteria.builder()
                .nome(nome)
                .jogoId(jogo)
                .atletaId(atleta)
                .build();

        return ResponseEntity.ok()
                .body(equipeService.findAllPage(equipeCriteria, page, linesPerPage, orderBy, direction)
                        .map(equipe -> equipeMapper.mapToDTO(equipe, EquipeDTO.class)));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody EquipeDTO equipeDTO, @PathVariable int id) {
        Equipe equipe = equipeMapper.mapToModel(equipeDTO, Equipe.class);
        equipe.setId(id);
        equipeService.update(equipe);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody EquipeDTO equipeDTO) {
        if (userSecurityService.authenticated() != null)
            equipeDTO.setUser(UserDTO.builder().id(userSecurityService.authenticated().getId()).build());
        Equipe equipe = equipeMapper.mapToModel(equipeDTO, Equipe.class);
        equipe = equipeService.insert(equipe);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(equipe.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable int id) {
        equipeService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
