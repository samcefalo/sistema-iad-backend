package me.samcefalo.sistemaiadbackend.resources;

import me.samcefalo.sistemaiadbackend.dtos.AcaoDTO;
import me.samcefalo.sistemaiadbackend.dtos.UserDTO;
import me.samcefalo.sistemaiadbackend.mappers.AcaoMapper;
import me.samcefalo.sistemaiadbackend.models.Acao;
import me.samcefalo.sistemaiadbackend.services.AcaoService;
import me.samcefalo.sistemaiadbackend.services.UserSecurityService;
import me.samcefalo.sistemaiadbackend.specifications.criterias.AcaoCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

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

    /*
    Retorna Componentes da lista para não perder propriedade do @JsonTypeInfo
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<AcaoDTO>> findPage(@Valid AcaoCriteria acaoCriteria,
                                                  Pageable pageable) {

        return ResponseEntity.ok()
                .body(acaoService.findAllPage(acaoCriteria, pageable)
                        .map(acao -> acaoMapper.mapToDTO(acao, AcaoDTO.class)).getContent());
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
