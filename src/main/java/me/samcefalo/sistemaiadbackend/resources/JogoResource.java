package me.samcefalo.sistemaiadbackend.resources;

import me.samcefalo.sistemaiadbackend.dtos.JogoDTO;
import me.samcefalo.sistemaiadbackend.mappers.JogoMapper;
import me.samcefalo.sistemaiadbackend.models.Jogo;
import me.samcefalo.sistemaiadbackend.services.JogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/jogos")
public class JogoResource {

    @Autowired
    private JogoService jogoService;
    @Autowired
    private JogoMapper jogoMapper;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<JogoDTO> find(@PathVariable int id) {
        Jogo jogo = jogoService.find(id);
        return ResponseEntity.ok().body(jogoMapper.mapTo(jogo));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<JogoDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") int page,
                                                  @RequestParam(value = "linesPerPage", defaultValue = "24") int linesPerPage,
                                                  @RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
                                                  @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        return ResponseEntity.ok()
                .body(jogoService.findPage(page, linesPerPage, orderBy, direction)
                        .map(jogo -> jogoMapper.mapTo(jogo)));
    }

    @RequestMapping(value = "/categoria/{categoria}", method = RequestMethod.GET)
    public ResponseEntity<Page<JogoDTO>> findPageCategoria(@PathVariable String categoria,
                                                           @RequestParam(value = "page", defaultValue = "0") int page,
                                                           @RequestParam(value = "linesPerPage", defaultValue = "24") int linesPerPage,
                                                           @RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
                                                           @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        return ResponseEntity.ok()
                .body(jogoService.findPageCategoria(categoria, page, linesPerPage, orderBy, direction)
                        .map(jogo -> jogoMapper.mapTo(jogo)));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody JogoDTO jogoDTO) {
        Jogo jogo = jogoMapper.mapTo(jogoDTO);
        jogo = jogoService.insert(jogo);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(jogo.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody JogoDTO jogoDTO, @PathVariable int id) {
        Jogo jogo = jogoMapper.mapTo(jogoDTO);
        jogo.setId(id);
        jogoService.update(jogo);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable int id) {
        jogoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
