package me.samcefalo.sistemaiadbackend.resources;

import me.samcefalo.sistemaiadbackend.dtos.*;
import me.samcefalo.sistemaiadbackend.mappers.AcaoMapper;
import me.samcefalo.sistemaiadbackend.mappers.AtletaMapper;
import me.samcefalo.sistemaiadbackend.mappers.EquipeMapper;
import me.samcefalo.sistemaiadbackend.mappers.JogoMapper;
import me.samcefalo.sistemaiadbackend.models.Equipe;
import me.samcefalo.sistemaiadbackend.services.EquipeService;
import me.samcefalo.sistemaiadbackend.services.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/equipes")
public class EquipeResource {

    @Autowired
    private UserSecurityService userSecurityService;
    @Autowired
    private EquipeService equipeService;
    @Autowired
    private EquipeMapper equipeMapper;
    @Autowired
    private AcaoMapper acaoMapper;
    @Autowired
    private JogoMapper jogoMapper;
    @Autowired
    private AtletaMapper atletaMapper;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<EquipeDTO> find(@PathVariable int id) {
        Equipe equipe = equipeService.find(id);
        return ResponseEntity.ok().body(equipeMapper.mapToDTO(equipe, EquipeDTO.class));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<EquipeDTO>> findAll() {
        return ResponseEntity.ok()
                .body(equipeService.findAll().stream()
                        .map(equipe -> equipeMapper.mapToDTO(equipe, EquipeDTO.class))
                        .collect(Collectors.toList()));
    }


    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<EquipeDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") int page,
                                                    @RequestParam(value = "linesPerPage", defaultValue = "24") int linesPerPage,
                                                    @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
                                                    @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        return ResponseEntity.ok()
                .body(equipeService.findPage(page, linesPerPage, orderBy, direction)
                        .map(equipe -> equipeMapper.mapToDTO(equipe, EquipeDTO.class)));
    }

    @RequestMapping(value = "/{id}/acoes", method = RequestMethod.GET)
    public ResponseEntity<Page<AcaoDTO>> findAcoesPage(@PathVariable int id, @RequestParam(value = "page", defaultValue = "0") int page,
                                                       @RequestParam(value = "linesPerPage", defaultValue = "24") int linesPerPage,
                                                       @RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
                                                       @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        return ResponseEntity.ok()
                .body(equipeService.findAcoesPage(id, page, linesPerPage, orderBy, direction)
                        .map(acao -> acaoMapper.mapToDTO(acao, AcaoDTO.class)));
    }

    @RequestMapping(value = "/{id}/atletas", method = RequestMethod.GET)
    public ResponseEntity<Page<AtletaDTO>> findAtletasPage(@PathVariable int id, @RequestParam(value = "page", defaultValue = "0") int page,
                                                           @RequestParam(value = "linesPerPage", defaultValue = "24") int linesPerPage,
                                                           @RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
                                                           @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        return ResponseEntity.ok()
                .body(equipeService.findAtletlas(id, page, linesPerPage, orderBy, direction)
                        .map(atleta -> atletaMapper.mapToDTO(atleta, AtletaDTO.class)));
    }

    @RequestMapping(value = "/{id}/jogos", method = RequestMethod.GET)
    public ResponseEntity<Page<JogoDTO>> findJogosPage(@PathVariable int id, @RequestParam(value = "page", defaultValue = "0") int page,
                                                       @RequestParam(value = "linesPerPage", defaultValue = "24") int linesPerPage,
                                                       @RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
                                                       @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        return ResponseEntity.ok()
                .body(equipeService.findJogosPage(id, page, linesPerPage, orderBy, direction)
                        .map(jogo -> jogoMapper.mapToDTO(jogo, JogoDTO.class)));
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
