package me.samcefalo.sistemaiadbackend.resources;

import me.samcefalo.sistemaiadbackend.domain.Jogador;
import me.samcefalo.sistemaiadbackend.domain.dto.JogadorDTO;
import me.samcefalo.sistemaiadbackend.services.JogadorService;
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

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Jogador> find(@PathVariable int id) {
        Jogador jogador = jogadorService.find(id);
        return ResponseEntity.ok().body(jogador);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<JogadorDTO>> findAll() {
        return ResponseEntity.ok()
                .body(jogadorService.findAll()
                        .stream().map(jogador -> new JogadorDTO(jogador)).collect(Collectors.toList()));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody JogadorDTO jogadorDTO) {
        Jogador jogador = jogadorService.fromDTO(jogadorDTO);
        jogador = jogadorService.insert(jogador);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(jogador.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody JogadorDTO jogadorDTO, @PathVariable int id) {
        Jogador jogador = jogadorService.fromDTO(jogadorDTO);
        jogador.setId(id);
        jogadorService.update(jogador);
        return ResponseEntity.noContent().build();
    }

}
