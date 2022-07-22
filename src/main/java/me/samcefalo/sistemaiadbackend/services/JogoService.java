package me.samcefalo.sistemaiadbackend.services;import me.samcefalo.sistemaiadbackend.models.Equipe;import me.samcefalo.sistemaiadbackend.models.Jogo;import me.samcefalo.sistemaiadbackend.repositories.JogoRepository;import me.samcefalo.sistemaiadbackend.services.exceptions.AuthorizationException;import me.samcefalo.sistemaiadbackend.services.exceptions.ObjectNotFoundException;import me.samcefalo.sistemaiadbackend.specifications.JogoSpecification;import me.samcefalo.sistemaiadbackend.specifications.criterias.JogoCriteria;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.data.domain.Page;import org.springframework.data.domain.Pageable;import org.springframework.data.jpa.domain.Specification;import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;import java.time.LocalDate;import java.util.List;@Servicepublic class JogoService {    @Autowired    private JogoRepository jogoRepository;    @Autowired    private AcaoService acaoService;    @Autowired    private UserSecurityService userSecurityService;    @Autowired    private EquipeService equipeService;    public Jogo find(int id) {        return jogoRepository.findById(id)                .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado. ID: " + id + ", Tipo: " + Jogo.class.getSimpleName()));    }    public Jogo insert(Jogo jogo) {        if (!userSecurityService.isAuth(jogo)) {            throw new AuthorizationException("Acesso negado.");        }        jogo.setData(LocalDate.now());        jogo.getAtletas().clear();        for (Equipe equipe : jogo.getEquipes()) {            equipe = equipeService.find(equipe.getId());            jogo.getAtletas()                    .addAll(equipe.getAtletas());        }        return jogoRepository.save(jogo);    }    public Jogo update(Jogo jogo) {        Jogo jogoDB = find(jogo.getId());        if (!userSecurityService.isAuth(jogoDB)) {            throw new AuthorizationException("Acesso negado.");        }        updateData(jogoDB, jogo);        return jogoRepository.save(jogoDB);    }    private void updateData(Jogo jogoDB, Jogo jogo) {        jogoDB.setEsporte(jogo.getEsporte());        jogoDB.setEquipes(jogo.getEquipes());        jogoDB.setTipo(jogo.getTipo());        jogoDB.setEsporte(jogo.getEsporte());        jogo.getAtletas().clear();        for (Equipe equipe : jogo.getEquipes()) {            equipe = equipeService.find(equipe.getId());            jogo.getAtletas()                    .addAll(equipe.getAtletas());        }    }    public void delete(int id) {        Jogo jogo = find(id);        if (!userSecurityService.isAuth(jogo)) {            throw new AuthorizationException("Acesso negado.");        }        delete(jogo);    }    private void delete(Jogo jogo) {        jogo.getAcoes().stream()                .forEach(acao -> acaoService.delete(acao.getId()));        jogoRepository.delete(jogo);    }    @Transactional(readOnly = true)    public List<Jogo> findAll(JogoCriteria jogoCriteria) {        jogoCriteria.setUser(userSecurityService.authenticatedUser().getId());        Specification specification = new JogoSpecification(jogoCriteria);        return jogoRepository.findAll(specification);    }    @Transactional(readOnly = true)    public Page<Jogo> findAllPage(JogoCriteria jogoCriteria, Pageable pageable) {        jogoCriteria.setUser(userSecurityService.authenticatedUser().getId());        Specification specification = new JogoSpecification(jogoCriteria);        return jogoRepository.findAll(specification, pageable);    }}