package me.samcefalo.sistemaiadbackend.resources;

import me.samcefalo.sistemaiadbackend.domain.Jogo;
import me.samcefalo.sistemaiadbackend.domain.dto.JogoDTO;
import me.samcefalo.sistemaiadbackend.services.JogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/jogos")
public class JogoResource {

    @Autowired
    private JogoService jogoService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Jogo> find(@PathVariable int id) {
        Jogo jogo = jogoService.find(id);
        return ResponseEntity.ok().body(jogo);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<JogoDTO>> findAll() {
        return ResponseEntity.ok()
                .body(jogoService.findAll()
                        .stream().map(jogo -> new JogoDTO(jogo)).collect(Collectors.toList()));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody JogoDTO jogoDTO) {
        Jogo jogo = jogoService.fromDTO(jogoDTO);
        jogo = jogoService.insert(jogo);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(jogo.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody JogoDTO jogoDTO, @PathVariable int id) {
        Jogo jogo = jogoService.fromDTO(jogoDTO);
        jogo.setId(id);
        jogoService.update(jogo);
        return ResponseEntity.noContent().build();
    }

}
