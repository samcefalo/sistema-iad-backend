package me.samcefalo.sistemaiadbackend.resources;

import me.samcefalo.sistemaiadbackend.domain.Acao;
import me.samcefalo.sistemaiadbackend.domain.Equipe;
import me.samcefalo.sistemaiadbackend.domain.Jogo;
import me.samcefalo.sistemaiadbackend.domain.dto.AcaoDTO;
import me.samcefalo.sistemaiadbackend.domain.dto.EquipeDTO;
import me.samcefalo.sistemaiadbackend.domain.dto.JogoDTO;
import me.samcefalo.sistemaiadbackend.services.EquipeService;
import me.samcefalo.sistemaiadbackend.services.mappers.AcaoMappers;
import me.samcefalo.sistemaiadbackend.services.mappers.EquipeMappers;
import me.samcefalo.sistemaiadbackend.services.mappers.JogoMappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/equipes")
public class EquipeResource {

    @Autowired
    private EquipeService equipeService;
    @Autowired
    private EquipeMappers mappers;
    @Autowired
    private AcaoMappers acaoMappers;
    @Autowired
    private JogoMappers jogoMappers;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<EquipeDTO> find(@PathVariable int id) {
        Equipe equipe = equipeService.find(id);
        return ResponseEntity.ok().body(mappers.equipeToEquipeDto(equipe));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<EquipeDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") int page,
                                                    @RequestParam(value = "linesPerPage", defaultValue = "24") int linesPerPage,
                                                    @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
                                                    @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        return ResponseEntity.ok()
                .body(equipeService.findPage(page, linesPerPage, orderBy, direction)
                        .map(equipe -> mappers.equipeToEquipeDto(equipe)));
    }

    @RequestMapping(value = "/{id}/acoes", method = RequestMethod.GET)
    public ResponseEntity<List<AcaoDTO>> findAcoes(@PathVariable int id) {
        List<Acao> acoes = equipeService.findAcoes(id);
        return ResponseEntity.ok().body(acaoMappers.acoesToAcoesDto(acoes));
    }

    @RequestMapping(value = "/{id}/jogos", method = RequestMethod.GET)
    public ResponseEntity<List<JogoDTO>> findJogos(@PathVariable int id) {
        List<Jogo> jogos = equipeService.findJogos(id);
        return ResponseEntity.ok().body(jogoMappers.getListJogoDto(jogos));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody EquipeDTO equipeDTO, @PathVariable int id) {
        Equipe equipe = mappers.equipeDtoToEquipe(equipeDTO);
        equipe.setId(id);
        equipeService.update(equipe);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody EquipeDTO equipeDTO) {
        Equipe equipe = mappers.equipeDtoToEquipe(equipeDTO);
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
