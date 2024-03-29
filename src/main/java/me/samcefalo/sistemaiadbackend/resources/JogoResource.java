package me.samcefalo.sistemaiadbackend.resources;

import me.samcefalo.sistemaiadbackend.dtos.JogoDTO;
import me.samcefalo.sistemaiadbackend.dtos.UserDTO;
import me.samcefalo.sistemaiadbackend.mappers.JogoMapper;
import me.samcefalo.sistemaiadbackend.models.Jogo;
import me.samcefalo.sistemaiadbackend.services.JogoService;
import me.samcefalo.sistemaiadbackend.services.UserSecurityService;
import me.samcefalo.sistemaiadbackend.specifications.criterias.JogoCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    @Autowired
    private UserSecurityService userSecurityService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<JogoDTO> find(@PathVariable int id) {
        Jogo jogo = jogoService.find(id);
        return ResponseEntity.ok().body(jogoMapper.mapToDTO(jogo, JogoDTO.class));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<JogoDTO>> findPage(@Valid JogoCriteria jogoCriteria, Pageable pageable) {

        return ResponseEntity.ok()
                .body(jogoService.findAllPage(jogoCriteria, pageable)
                        .map(jogo -> jogoMapper.mapToDTO(jogo, JogoDTO.class)));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody JogoDTO jogoDTO) {
        jogoDTO.setUser(UserDTO.builder().id(userSecurityService.authenticated().getId()).build());
        Jogo jogo = jogoMapper.mapToModel(jogoDTO, Jogo.class);
        jogo = jogoService.insert(jogo);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(jogo.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody JogoDTO jogoDTO, @PathVariable int id) {
        Jogo jogo = jogoMapper.mapToModel(jogoDTO, Jogo.class);
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
