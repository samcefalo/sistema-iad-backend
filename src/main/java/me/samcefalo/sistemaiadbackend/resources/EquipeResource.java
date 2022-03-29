package me.samcefalo.sistemaiadbackend.resources;

import me.samcefalo.sistemaiadbackend.domain.Acao;
import me.samcefalo.sistemaiadbackend.domain.Equipe;
import me.samcefalo.sistemaiadbackend.domain.Jogo;
import me.samcefalo.sistemaiadbackend.domain.dto.AcaoDTO;
import me.samcefalo.sistemaiadbackend.domain.dto.EquipeDTO;
import me.samcefalo.sistemaiadbackend.domain.dto.JogoDTO;
import me.samcefalo.sistemaiadbackend.services.AcaoService;
import me.samcefalo.sistemaiadbackend.services.EquipeService;
import me.samcefalo.sistemaiadbackend.services.JogoService;
import me.samcefalo.sistemaiadbackend.services.mappers.EquipeMappers;
import org.springframework.beans.factory.annotation.Autowired;
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
    private EquipeService equipeService;
    @Autowired
    private EquipeMappers mappers;
    @Autowired
    private AcaoService acaoService;
    @Autowired
    private JogoService jogoService;

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

    @RequestMapping(value = "/{id}/acoes", method = RequestMethod.GET)
    public ResponseEntity<List<AcaoDTO>> findAcoes(@PathVariable int id) {
        List<Acao> acoes = equipeService.findAcoes(id);
        return ResponseEntity.ok().body(acaoService.getListAcaoDto(acoes));
    }

    @RequestMapping(value = "/{id}/jogos", method = RequestMethod.GET)
    public ResponseEntity<List<JogoDTO>> findJogos(@PathVariable int id) {
        List<Jogo> jogos = equipeService.findJogos(id);
        return ResponseEntity.ok().body(jogoService.getListJogoDto(jogos));
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
