package me.samcefalo.sistemaiadbackend.resources;

import me.samcefalo.sistemaiadbackend.domain.Acao;
import me.samcefalo.sistemaiadbackend.domain.Jogador;
import me.samcefalo.sistemaiadbackend.domain.Jogo;
import me.samcefalo.sistemaiadbackend.domain.dto.AcaoDTO;
import me.samcefalo.sistemaiadbackend.domain.dto.JogadorDTO;
import me.samcefalo.sistemaiadbackend.domain.dto.JogoDTO;
import me.samcefalo.sistemaiadbackend.mappers.EntidadeMappers;
import me.samcefalo.sistemaiadbackend.services.AcaoService;
import me.samcefalo.sistemaiadbackend.services.JogadorService;
import me.samcefalo.sistemaiadbackend.services.JogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/jogadores")
public class JogadorResource {

    @Autowired
    private JogadorService jogadorService;
    @Autowired
    private EntidadeMappers mappers;
    @Autowired
    private AcaoService acaoService;
    @Autowired
    private JogoService jogoService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<JogadorDTO> find(@PathVariable int id) {
        Jogador jogador = jogadorService.find(id);
        return ResponseEntity.ok().body(mappers.jogadorToJogadorDto(jogador));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<JogadorDTO>> findAll() {
        return ResponseEntity.ok()
                .body(jogadorService.findAll()
                        .stream().map(jogador -> mappers.jogadorToJogadorDto(jogador))
                        .collect(Collectors.toList()));
    }

    @RequestMapping(value = "/{id}/acoes", method = RequestMethod.GET)
    public ResponseEntity<List<AcaoDTO>> findAcoes(@PathVariable int id) {
        List<Acao> acoes = jogadorService.findAcoes(id);
        return ResponseEntity.ok().body(acaoService.getListAcaoDto(acoes));
    }

    @RequestMapping(value = "/{id}/jogos", method = RequestMethod.GET)
    public ResponseEntity<List<JogoDTO>> findJogos(@PathVariable int id) {
        List<Jogo> jogos = jogadorService.findJogos(id);
        return ResponseEntity.ok().body(jogoService.getListJogoDto(jogos));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody JogadorDTO jogadorDTO) {
        Jogador jogador = mappers.jogadorDtoToJogador(jogadorDTO);
        jogador = jogadorService.insert(jogador);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(jogador.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody JogadorDTO jogadorDTO, @PathVariable int id) {
        Jogador jogador = mappers.jogadorDtoToJogador(jogadorDTO);
        jogador.setId(id);
        jogadorService.update(jogador);
        return ResponseEntity.noContent().build();
    }

}
