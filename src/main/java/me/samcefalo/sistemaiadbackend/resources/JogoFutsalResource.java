package me.samcefalo.sistemaiadbackend.resources;

import me.samcefalo.sistemaiadbackend.domain.JogoFutsal;
import me.samcefalo.sistemaiadbackend.domain.dto.JogoFutsalDTO;
import me.samcefalo.sistemaiadbackend.services.JogoFutsalService;
import me.samcefalo.sistemaiadbackend.services.mappers.JogoMappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/jogos/futsal")
public class JogoFutsalResource {

    @Autowired
    private JogoFutsalService jogoFutsalService;
    @Autowired
    private JogoMappers mappers;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<JogoFutsalDTO> find(@PathVariable int id) {
        JogoFutsal jogoFutsal = jogoFutsalService.find(id);
        return ResponseEntity.ok().body(mappers.jogoFutsalToJogoFutsalDto(jogoFutsal));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<JogoFutsalDTO>> findAll() {
        return ResponseEntity.ok()
                .body(jogoFutsalService.findAll()
                        .stream().map(jogoFutsal -> mappers.jogoFutsalToJogoFutsalDto(jogoFutsal))
                        .collect(Collectors.toList()));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody JogoFutsalDTO jogoFutsalDTO) {
        JogoFutsal jogoFutsal = mappers.jogoFutsalDtoToJogoFutsal(jogoFutsalDTO);
        jogoFutsal = jogoFutsalService.insert(jogoFutsal);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(jogoFutsal.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody JogoFutsalDTO jogoFutsalDTO, @PathVariable int id) {
        JogoFutsal jogoFutsal = mappers.jogoFutsalDtoToJogoFutsal(jogoFutsalDTO);
        jogoFutsal.setId(id);
        jogoFutsalService.update(jogoFutsal);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable int id) {
        jogoFutsalService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
