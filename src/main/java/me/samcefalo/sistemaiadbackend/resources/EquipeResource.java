package me.samcefalo.sistemaiadbackend.resources;

import me.samcefalo.sistemaiadbackend.domain.Equipe;
import me.samcefalo.sistemaiadbackend.domain.dto.EquipeDTO;
import me.samcefalo.sistemaiadbackend.mappers.EquipeMappers;
import me.samcefalo.sistemaiadbackend.services.EquipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/equipes")
public class EquipeResource {

    @Autowired
    private EquipeService equipeService;
    @Autowired
    private EquipeMappers mappers;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<EquipeDTO> find(@PathVariable int id) {
        Equipe equipe = equipeService.find(id);
        return ResponseEntity.ok().body(mappers.equipeToEquipeDto(equipe));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<EquipeDTO>> findAll() {
        return ResponseEntity.ok()
                .body(equipeService.findAll()
                        .stream().map(equipe -> mappers.equipeToEquipeDto(equipe))
                        .collect(Collectors.toList()));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody EquipeDTO equipeDTO) {
        Equipe equipe = mappers.equipeDtoToEquipe(equipeDTO);
        equipe = equipeService.insert(equipe);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(equipe.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}
