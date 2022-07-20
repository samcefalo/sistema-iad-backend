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
import java.time.LocalDate;
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
    public ResponseEntity<List<AcaoDTO>> findPage(@RequestParam(value = "placar", required = false) String placar,
                                                  @RequestParam(value = "categoria", required = false) String categoria,
                                                  @RequestParam(value = "grauDificuldade", defaultValue = "0") Integer grauDificuldade,
                                                  @RequestParam(value = "esporte", defaultValue = "0") Integer esporte,
                                                  @RequestParam(value = "tipoJogo", defaultValue = "0") Integer tipoJogo,
                                                  @RequestParam(value = "area", defaultValue = "0") Integer area,
                                                  @RequestParam(value = "etapa", defaultValue = "0") Integer etapa,
                                                  @RequestParam(value = "tempoInsercao", defaultValue = "0") Integer tempoInsercao,
                                                  @RequestParam(value = "data", required = false) LocalDate data,
                                                  @RequestParam(value = "tempo", required = false) Integer tempo,
                                                  @RequestParam(value = "jogo", required = false) Integer jogo,
                                                  @RequestParam(value = "equipe", required = false) Integer equipe,
                                                  @RequestParam(value = "atleta", required = false) Integer atleta,
                                                  @RequestParam(value = "exito", required = false) Boolean exito,
                                                  @RequestParam(value = "gol", required = false) Boolean gol,
                                                  @RequestParam(value = "posseDeBola", required = false) Boolean posseDeBola,
                                                  Pageable pageable) {

        AcaoCriteria acaoCriteria = AcaoCriteria.builder()
                .placar(placar).categoria(categoria)
                .grauDificuldade(grauDificuldade).area(area)
                .tempo(tempo).etapa(etapa)
                .jogoId(jogo).equipeId(equipe)
                .atletaId(atleta).exito(exito)
                .gol(gol).posseDeBola(posseDeBola)
                .tipoJogo(tipoJogo).esporte(esporte)
                .tempoInsercao(tempoInsercao).data(data)
                .build();

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
