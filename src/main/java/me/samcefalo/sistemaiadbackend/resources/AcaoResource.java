package me.samcefalo.sistemaiadbackend.resources;

import me.samcefalo.sistemaiadbackend.dtos.AcaoDTO;
import me.samcefalo.sistemaiadbackend.dtos.UserDTO;
import me.samcefalo.sistemaiadbackend.mappers.AcaoMapper;
import me.samcefalo.sistemaiadbackend.models.Acao;
import me.samcefalo.sistemaiadbackend.services.AcaoService;
import me.samcefalo.sistemaiadbackend.services.UserSecurityService;
import me.samcefalo.sistemaiadbackend.specifications.criterias.AcaoCriteria;
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
    public ResponseEntity<Page<AcaoDTO>> findPage(@RequestParam(value = "placar", required = false) String placar,
                                                  @RequestParam(value = "categoria", required = false) String categoria,
                                                  @RequestParam(value = "grauDificuldade", defaultValue = "0") Integer grauDificuldade,
                                                  @RequestParam(value = "area", defaultValue = "0") Integer area,
                                                  @RequestParam(value = "etapa", defaultValue = "0") Integer etapa,
                                                  @RequestParam(value = "tempo", required = false) Integer tempo,
                                                  @RequestParam(value = "jogo", required = false) Integer jogo,
                                                  @RequestParam(value = "equipe", required = false) Integer equipe,
                                                  @RequestParam(value = "atleta", required = false) Integer atleta,
                                                  @RequestParam(value = "exito", required = false) Boolean exito,
                                                  @RequestParam(value = "page", defaultValue = "0") int page,
                                                  @RequestParam(value = "linesPerPage", defaultValue = "24") int linesPerPage,
                                                  @RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
                                                  @RequestParam(value = "direction", defaultValue = "ASC") String direction) {

        AcaoCriteria acaoCriteria = AcaoCriteria.builder()
                .placar(placar).categoria(categoria)
                .grauDificuldade(grauDificuldade).area(area)
                .tempo(tempo).etapa(etapa)
                .jogoId(jogo).equipeId(equipe)
                .atletaId(atleta).exito(exito)
                .build();
        return ResponseEntity.ok()
                .body(acaoService.findAllPage(acaoCriteria, page, linesPerPage, orderBy, direction)
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
