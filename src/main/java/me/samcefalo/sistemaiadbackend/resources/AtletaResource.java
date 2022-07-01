package me.samcefalo.sistemaiadbackend.resources;

import me.samcefalo.sistemaiadbackend.dtos.AcaoDTO;
import me.samcefalo.sistemaiadbackend.dtos.AtletaDTO;
import me.samcefalo.sistemaiadbackend.dtos.JogoDTO;
import me.samcefalo.sistemaiadbackend.dtos.UserDTO;
import me.samcefalo.sistemaiadbackend.mappers.AcaoMapper;
import me.samcefalo.sistemaiadbackend.mappers.AtletaMapper;
import me.samcefalo.sistemaiadbackend.mappers.JogoMapper;
import me.samcefalo.sistemaiadbackend.models.Atleta;
import me.samcefalo.sistemaiadbackend.services.AtletaService;
import me.samcefalo.sistemaiadbackend.services.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    private AtletaMapper entidadeMapper;
    @Autowired
    private AcaoMapper acaoMapper;
    @Autowired
    private JogoMapper jogoMapper;
    @Autowired
    private UserSecurityService userSecurityService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<AtletaDTO> find(@PathVariable int id) {
        Atleta atleta = atletaService.find(id);
        return ResponseEntity.ok().body((AtletaDTO) entidadeMapper.mapToDTO(atleta, AtletaDTO.class));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<AtletaDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") int page,
                                                    @RequestParam(value = "linesPerPage", defaultValue = "24") int linesPerPage,
                                                    @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
                                                    @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        return ResponseEntity.ok()
                .body(atletaService.findPage(page, linesPerPage, orderBy, direction)
                        .map(atleta -> (AtletaDTO) entidadeMapper.mapToDTO(atleta, AtletaDTO.class)));
    }

    @RequestMapping(value = "/{id}/acoes", method = RequestMethod.GET)
    public ResponseEntity<Page<AcaoDTO>> findAcoesPage(@PathVariable int id, @RequestParam(value = "page", defaultValue = "0") int page,
                                                       @RequestParam(value = "linesPerPage", defaultValue = "24") int linesPerPage,
                                                       @RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
                                                       @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        return ResponseEntity.ok()
                .body(atletaService.findAcoesPage(id, page, linesPerPage, orderBy, direction)
                        .map(acao -> acaoMapper.mapToDTO(acao, AcaoDTO.class)));
    }

    @RequestMapping(value = "/{id}/jogos", method = RequestMethod.GET)
    public ResponseEntity<Page<JogoDTO>> findJogosPage(@PathVariable int id, @RequestParam(value = "page", defaultValue = "0") int page,
                                                       @RequestParam(value = "linesPerPage", defaultValue = "24") int linesPerPage,
                                                       @RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
                                                       @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        return ResponseEntity.ok()
                .body(atletaService.findJogosPage(id, page, linesPerPage, orderBy, direction)
                        .map(jogo -> jogoMapper.mapToDTO(jogo, JogoDTO.class)));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody AtletaDTO atletaDTO) {
        if (userSecurityService.authenticated() != null)
            atletaDTO.setUser(UserDTO.builder().id(userSecurityService.authenticated().getId()).build());
        Atleta atleta = (Atleta) entidadeMapper.mapToModel(atletaDTO, Atleta.class);
        atleta = atletaService.insert(atleta);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(atleta.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody AtletaDTO atletaDTO, @PathVariable int id) {
        Atleta atleta = (Atleta) entidadeMapper.mapToModel(atletaDTO, Atleta.class);
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
