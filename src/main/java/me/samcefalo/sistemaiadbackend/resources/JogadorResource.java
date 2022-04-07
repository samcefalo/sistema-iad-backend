package me.samcefalo.sistemaiadbackend.resources;

import me.samcefalo.sistemaiadbackend.dtos.AcaoDTO;
import me.samcefalo.sistemaiadbackend.dtos.JogadorDTO;
import me.samcefalo.sistemaiadbackend.dtos.JogoDTO;
import me.samcefalo.sistemaiadbackend.mappers.AcaoMapper;
import me.samcefalo.sistemaiadbackend.mappers.EntidadeMapper;
import me.samcefalo.sistemaiadbackend.mappers.JogoMapper;
import me.samcefalo.sistemaiadbackend.models.Jogador;
import me.samcefalo.sistemaiadbackend.services.JogadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/jogadores")
public class JogadorResource {

    @Autowired
    private JogadorService jogadorService;
    @Autowired
    private EntidadeMapper entidadeMapper;
    @Autowired
    private AcaoMapper acaoMapper;
    @Autowired
    private JogoMapper jogoMapper;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<JogadorDTO> find(@PathVariable int id) {
        Jogador jogador = jogadorService.find(id);
        return ResponseEntity.ok().body((JogadorDTO) entidadeMapper.mapToDTO(jogador, JogadorDTO.class));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<JogadorDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") int page,
                                                     @RequestParam(value = "linesPerPage", defaultValue = "24") int linesPerPage,
                                                     @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
                                                     @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        return ResponseEntity.ok()
                .body(jogadorService.findPage(page, linesPerPage, orderBy, direction)
                        .map(jogador -> (JogadorDTO) entidadeMapper.mapToDTO(jogador, JogadorDTO.class)));
    }

    @RequestMapping(value = "/{id}/acoes", method = RequestMethod.GET)
    public ResponseEntity<Page<AcaoDTO>> findAcoesPage(@PathVariable int id, @RequestParam(value = "page", defaultValue = "0") int page,
                                                       @RequestParam(value = "linesPerPage", defaultValue = "24") int linesPerPage,
                                                       @RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
                                                       @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        return ResponseEntity.ok()
                .body(jogadorService.findAcoesPage(id, page, linesPerPage, orderBy, direction)
                        .map(acao -> acaoMapper.mapToDTO(acao, AcaoDTO.class)));
    }

    @RequestMapping(value = "/{id}/jogos", method = RequestMethod.GET)
    public ResponseEntity<Page<JogoDTO>> findJogosPage(@PathVariable int id, @RequestParam(value = "page", defaultValue = "0") int page,
                                                       @RequestParam(value = "linesPerPage", defaultValue = "24") int linesPerPage,
                                                       @RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
                                                       @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        return ResponseEntity.ok()
                .body(jogadorService.findJogosPage(id, page, linesPerPage, orderBy, direction)
                        .map(jogo -> jogoMapper.mapToDTO(jogo, JogoDTO.class)));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody JogadorDTO jogadorDTO) {
        Jogador jogador = (Jogador) entidadeMapper.mapToModel(jogadorDTO, Jogador.class);
        jogador = jogadorService.insert(jogador);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(jogador.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody JogadorDTO jogadorDTO, @PathVariable int id) {
        Jogador jogador = (Jogador) entidadeMapper.mapToModel(jogadorDTO, Jogador.class);
        jogador.setId(id);
        jogadorService.update(jogador);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable int id) {
        jogadorService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
