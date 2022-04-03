package me.samcefalo.sistemaiadbackend.resources;

import me.samcefalo.sistemaiadbackend.domain.Jogador;
import me.samcefalo.sistemaiadbackend.domain.dto.AcaoDTO;
import me.samcefalo.sistemaiadbackend.domain.dto.JogadorDTO;
import me.samcefalo.sistemaiadbackend.domain.dto.JogoDTO;
import me.samcefalo.sistemaiadbackend.services.JogadorService;
import me.samcefalo.sistemaiadbackend.services.mappers.AcaoMappers;
import me.samcefalo.sistemaiadbackend.services.mappers.EntidadeMappers;
import me.samcefalo.sistemaiadbackend.services.mappers.JogoMappers;
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
    private EntidadeMappers mappers;
    @Autowired
    private AcaoMappers acaoMappers;
    @Autowired
    private JogoMappers jogoMappers;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<JogadorDTO> find(@PathVariable int id) {
        Jogador jogador = jogadorService.find(id);
        return ResponseEntity.ok().body(mappers.jogadorToJogadorDto(jogador));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<JogadorDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") int page,
                                                     @RequestParam(value = "linesPerPage", defaultValue = "24") int linesPerPage,
                                                     @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
                                                     @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        return ResponseEntity.ok()
                .body(jogadorService.findPage(page, linesPerPage, orderBy, direction)
                        .map(jogador -> mappers.jogadorToJogadorDto(jogador)));
    }

    @RequestMapping(value = "/{id}/acoes", method = RequestMethod.GET)
    public ResponseEntity<Page<AcaoDTO>> findAcoesPage(@PathVariable int id, @RequestParam(value = "page", defaultValue = "0") int page,
                                                       @RequestParam(value = "linesPerPage", defaultValue = "24") int linesPerPage,
                                                       @RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
                                                       @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        return ResponseEntity.ok()
                .body(jogadorService.findAcoesPage(id, page, linesPerPage, orderBy, direction)
                        .map(acao -> acaoMappers.acaoToAcaoDto(acao)));
    }

    @RequestMapping(value = "/{id}/jogos", method = RequestMethod.GET)
    public ResponseEntity<Page<JogoDTO>> findJogosPage(@PathVariable int id, @RequestParam(value = "page", defaultValue = "0") int page,
                                                       @RequestParam(value = "linesPerPage", defaultValue = "24") int linesPerPage,
                                                       @RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
                                                       @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        return ResponseEntity.ok()
                .body(jogadorService.findJogosPage(id, page, linesPerPage, orderBy, direction)
                        .map(jogo -> jogoMappers.jogoToJogoDto(jogo)));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody JogadorDTO jogadorDTO) {
        Jogador jogador = mappers.jogadorDtoToJogador(jogadorDTO);
        jogador = jogadorService.insert(jogador);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(jogador.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody JogadorDTO jogadorDTO, @PathVariable int id) {
        Jogador jogador = mappers.jogadorDtoToJogador(jogadorDTO);
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
