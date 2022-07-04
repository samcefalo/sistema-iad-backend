package me.samcefalo.sistemaiadbackend.services;import me.samcefalo.sistemaiadbackend.models.Equipe;import me.samcefalo.sistemaiadbackend.repositories.EquipeRepository;import me.samcefalo.sistemaiadbackend.repositories.JogoRepository;import me.samcefalo.sistemaiadbackend.services.exceptions.AuthorizationException;import me.samcefalo.sistemaiadbackend.services.exceptions.ObjectNotFoundException;import me.samcefalo.sistemaiadbackend.specifications.EquipeSpecification;import me.samcefalo.sistemaiadbackend.specifications.criterias.EquipeCriteria;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.data.domain.Page;import org.springframework.data.domain.PageRequest;import org.springframework.data.domain.Sort;import org.springframework.data.jpa.domain.Specification;import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;import java.util.List;@Servicepublic class EquipeService {    @Autowired    private EquipeRepository equipeRepository;    @Autowired    private AtletaService atletaService;    @Autowired    private AcaoService acaoService;    //Evitar circle dependency    @Autowired    private JogoRepository jogoRepository;    @Autowired    private UserSecurityService userSecurityService;    public Equipe find(int id) {        return equipeRepository.findById(id).orElseThrow(                () -> new ObjectNotFoundException("Objeto não encontrado. ID: " + id + ", Tipo: " + Equipe.class.getSimpleName()));    }    public Equipe insert(Equipe equipe) {        if (!userSecurityService.isAuth(equipe)) {            throw new AuthorizationException("Acesso negado.");        }        return equipeRepository.save(equipe);    }    public Equipe update(Equipe equipe) {        Equipe equipeDB = find(equipe.getId());        if (!userSecurityService.isAuth(equipeDB)) {            throw new AuthorizationException("Acesso negado.");        }        updateData(equipeDB, equipe);        return equipeRepository.save(equipeDB);    }    private void updateData(Equipe equipeDB, Equipe equipe) {        equipeDB.setNome(equipe.getNome());    }    public void delete(int id) {        Equipe equipe = find(id);        if (!userSecurityService.isAuth(equipe)) {            throw new AuthorizationException("Acesso negado.");        }        delete(equipe);    }    private void delete(Equipe equipe) {        equipe.getJogos()                .stream().forEach(jogo -> {                    jogo.getAtletas().removeIf(atleta -> equipe.getAtletas().contains(atleta));                    jogo.getEquipes().remove(equipe);                    jogoRepository.save(jogo);                });        equipe.getAtletas()                .stream().forEach(atleta -> atletaService.delete(atleta.getId()));        equipeRepository.delete(equipe);    }    @Transactional(readOnly = true)    public List<Equipe> findAll(EquipeCriteria equipeCriteria) {        equipeCriteria.setUserId(userSecurityService.authenticatedUser().getId());        Specification specification = new EquipeSpecification(equipeCriteria);        return equipeRepository.findAll(specification);    }    @Transactional(readOnly = true)    public Page<Equipe> findAllPage(EquipeCriteria equipeCriteria, int page, int linesPerPage, String orderBy, String direction) {        equipeCriteria.setUserId(userSecurityService.authenticatedUser().getId());        Specification specification = new EquipeSpecification(equipeCriteria);        PageRequest pageRequest = PageRequest.of(page, linesPerPage,                Sort.Direction.valueOf(direction.toUpperCase()), orderBy);        return equipeRepository.findAll(specification, pageRequest);    }}