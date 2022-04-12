package me.samcefalo.sistemaiadbackend.resources;

import me.samcefalo.sistemaiadbackend.dtos.AcaoDTO;
import me.samcefalo.sistemaiadbackend.dtos.UserDTO;
import me.samcefalo.sistemaiadbackend.mappers.AcaoMapper;
import me.samcefalo.sistemaiadbackend.models.Acao;
import me.samcefalo.sistemaiadbackend.services.AcaoService;
import me.samcefalo.sistemaiadbackend.services.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/acoes")
public class AcaoResource {

    @Autowired
    private AcaoService acaoService;
    @Autowired
    private AcaoMapper acaoMapper;
    @Autowired
    private UserSecurityService userSecurityService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<AcaoDTO> find(@PathVariable int id) {
        Acao acao = acaoService.find(id);

        return ResponseEntity.ok().body(acaoMapper.mapToDTO(acao, AcaoDTO.class));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<AcaoDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") int page,
                                                  @RequestParam(value = "linesPerPage", defaultValue = "24") int linesPerPage,
                                                  @RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
                                                  @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        return ResponseEntity.ok()
                .body(acaoService.findPage(page, linesPerPage, orderBy, direction)
                        .map(acao -> acaoMapper.mapToDTO(acao, AcaoDTO.class)));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody AcaoDTO acaoDTO) {
        if (userSecurityService.authenticated() != null)
            acaoDTO.setUser(UserDTO.builder().id(userSecurityService.authenticated().getId()).build());
        Acao acao = acaoMapper.mapToModel(acaoDTO, Acao.class);
        acao = acaoService.insert(acao);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(acao.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody AcaoDTO acaoDTO, @PathVariable int id) {
        Acao acao = acaoMapper.mapToModel(acaoDTO, Acao.class);
        acao.setId(id);
        acaoService.update(acao);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable int id) {
        acaoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
