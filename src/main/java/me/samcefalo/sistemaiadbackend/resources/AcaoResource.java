package me.samcefalo.sistemaiadbackend.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/acoes")
public class AcaoResource {
/* //TODO
    @Autowired
    private AcaoService acaoService;
    @Autowired
    private AcaoMappers mappers;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<AcaoDTO> find(@PathVariable int id) {
        Acao acao = acaoService.find(id);
        return ResponseEntity.ok().body(mappers.(jogoFutsal));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<JogoFutsalDTO>> findAll() {
        return ResponseEntity.ok()
                .body(jogoFutsalService.findAll()
                        .stream().map(jogoFutsal -> mappers.jogoFutsalToJogoFutsalDto(jogoFutsal))
                        .collect(Collectors.toList()));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody JogoFutsalDTO jogoFutsalDTO) {
        JogoFutsal jogoFutsal = mappers.jogoFutsalDtoToJogoFutsal(jogoFutsalDTO);
        jogoFutsal = jogoFutsalService.insert(jogoFutsal);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(jogoFutsal.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody JogoFutsalDTO jogoFutsalDTO, @PathVariable int id) {
        JogoFutsal jogoFutsal = mappers.jogoFutsalDtoToJogoFutsal(jogoFutsalDTO);
        jogoFutsal.setId(id);
        jogoFutsalService.update(jogoFutsal);
        return ResponseEntity.noContent().build();
    }


 */
}
