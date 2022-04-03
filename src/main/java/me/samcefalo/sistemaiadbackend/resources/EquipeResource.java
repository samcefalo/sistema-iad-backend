package me.samcefalo.sistemaiadbackend.resources;

import me.samcefalo.sistemaiadbackend.dtos.EquipeDTO;
import me.samcefalo.sistemaiadbackend.mappers.AcaoMapper;
import me.samcefalo.sistemaiadbackend.mappers.EquipeMapper;
import me.samcefalo.sistemaiadbackend.mappers.JogoMapper;
import me.samcefalo.sistemaiadbackend.models.Acao;
import me.samcefalo.sistemaiadbackend.models.Equipe;
import me.samcefalo.sistemaiadbackend.models.Jogo;
import me.samcefalo.sistemaiadbackend.services.EquipeService;
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
    private EquipeService equipeService;
    @Autowired
    private EquipeMapper equipeMapper;
    @Autowired
    private AcaoMapper acaoMapper;
    @Autowired
    private JogoMapper jogoMapper;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Equipe> find(@PathVariable int id) {
        Equipe equipe = equipeService.find(id);
        return ResponseEntity.ok().body(equipe);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<Equipe>> findPage(@RequestParam(value = "page", defaultValue = "0") int page,
                                                 @RequestParam(value = "linesPerPage", defaultValue = "24") int linesPerPage,
                                                 @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
                                                 @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        return ResponseEntity.ok()
                .body(equipeService.findPage(page, linesPerPage, orderBy, direction));
    }

    @RequestMapping(value = "/{id}/acoes", method = RequestMethod.GET)
    public ResponseEntity<Page<Acao>> findAcoesPage(@PathVariable int id, @RequestParam(value = "page", defaultValue = "0") int page,
                                                    @RequestParam(value = "linesPerPage", defaultValue = "24") int linesPerPage,
                                                    @RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
                                                    @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        return ResponseEntity.ok()
                .body(equipeService.findAcoesPage(id, page, linesPerPage, orderBy, direction));
    }

    @RequestMapping(value = "/{id}/jogos", method = RequestMethod.GET)
    public ResponseEntity<Page<Jogo>> findJogosPage(@PathVariable int id, @RequestParam(value = "page", defaultValue = "0") int page,
                                                    @RequestParam(value = "linesPerPage", defaultValue = "24") int linesPerPage,
                                                    @RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
                                                    @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        return ResponseEntity.ok()
                .body(equipeService.findJogosPage(id, page, linesPerPage, orderBy, direction));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody EquipeDTO equipeDTO, @PathVariable int id) {
        Equipe equipe = equipeMapper.mapTo(equipeDTO);
        equipe.setId(id);
        equipeService.update(equipe);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody EquipeDTO equipeDTO) {
        Equipe equipe = equipeMapper.mapTo(equipeDTO);
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
