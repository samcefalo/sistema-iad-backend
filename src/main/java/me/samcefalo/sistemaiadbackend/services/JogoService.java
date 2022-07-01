package me.samcefalo.sistemaiadbackend.services;import me.samcefalo.sistemaiadbackend.models.*;import me.samcefalo.sistemaiadbackend.repositories.JogoRepository;import me.samcefalo.sistemaiadbackend.services.exceptions.AuthorizationException;import me.samcefalo.sistemaiadbackend.services.exceptions.ObjectNotFoundException;import me.samcefalo.sistemaiadbackend.services.utils.ClassUtil;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.data.domain.Page;import org.springframework.data.domain.PageRequest;import org.springframework.data.domain.Sort;import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;import java.time.LocalDate;import java.util.List;@Servicepublic class JogoService {    @Autowired    private JogoRepository jogoRepository;    @Autowired    private AcaoService acaoService;    @Autowired    private ClassUtil classUtil;    @Autowired    private UserSecurityService userSecurityService;    @Autowired    private EquipeService equipeService;    public Jogo find(int id) {        return jogoRepository.findById(id)                .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado. ID: " + id + ", Tipo: " + Jogo.class.getSimpleName()));    }    public Jogo insert(Jogo jogo) {        if (!userSecurityService.isAuth(jogo)) {            throw new AuthorizationException("Acesso negado.");        }        jogo.setData(LocalDate.now());        jogo.getAtletas().clear();        for (Equipe equipe : jogo.getEquipes()) {            equipe = equipeService.find(equipe.getId());            jogo.getAtletas()                    .addAll(equipe.getAtletas());        }        return jogoRepository.save(jogo);    }    public Jogo update(Jogo jogo) {        Jogo jogoDB = find(jogo.getId());        if (!userSecurityService.isAuth(jogoDB)) {            throw new AuthorizationException("Acesso negado.");        }        updateData(jogoDB, jogo);        return jogoRepository.save(jogoDB);    }    private void updateData(Jogo jogoDB, Jogo jogo) {        jogoDB.setSituacaoJogo(jogo.getSituacaoJogo());        jogoDB.setTipoJogo(jogo.getTipoJogo());        jogoDB.setEquipes(jogo.getEquipes());        jogo.getAtletas().clear();        for (Equipe equipe : jogo.getEquipes()) {            equipe = equipeService.find(equipe.getId());            jogo.getAtletas()                    .addAll(equipe.getAtletas());        }    }    public void delete(int id) {        Jogo jogo = find(id);        if (!userSecurityService.isAuth(jogo)) {            throw new AuthorizationException("Acesso negado.");        }        delete(jogo);    }    private void delete(Jogo jogo) {        jogo.getAcoes().stream()                .forEach(acao -> acaoService.delete(acao.getId()));        jogoRepository.delete(jogo);    }    @Transactional(readOnly = true)    public Page<Jogo> findPage(int page, int linesPerPage, String orderBy, String direction) {        PageRequest pageRequest = PageRequest.of(page, linesPerPage,                Sort.Direction.valueOf(direction.toUpperCase()), orderBy);        return jogoRepository.findAllByUser(pageRequest, userSecurityService.authenticatedUser());    }    @Transactional(readOnly = true)    public Page<Jogo> findPageCategoria(int tipoJogo, int page, int linesPerPage, String orderBy, String direction) {        PageRequest pageRequest = PageRequest.of(page, linesPerPage,                Sort.Direction.valueOf(direction.toUpperCase()), orderBy);        return jogoRepository.findAllByTipoJogoAndUser(tipoJogo, pageRequest, userSecurityService.authenticatedUser());    }    @Transactional(readOnly = true)    public List<Jogo> findAll() {        return jogoRepository.findAllByUser(userSecurityService.authenticatedUser());    }    @Transactional(readOnly = true)    public List<Jogo> findAllByUser(User user) {        return jogoRepository.findAllByUser(user);    }    @Transactional(readOnly = true)    public List<Jogo> findByEquipes(Equipe equipe) {        return jogoRepository.findByEquipesAndUser(equipe, userSecurityService.authenticatedUser());    }    @Transactional(readOnly = true)    public Page<Jogo> findPageByEquipes(Equipe equipe, PageRequest pageRequest) {        return jogoRepository.findByEquipesAndUser(equipe, pageRequest, userSecurityService.authenticatedUser());    }    @Transactional    public List<Jogo> findByAtletas(Atleta atleta) {        return jogoRepository.findByAtletasAndUser(atleta, userSecurityService.authenticatedUser());    }    public List<Acao> findAcoes(int jogoId, Class<?> categoria) {        if (categoria.equals(Acao.class)) {            return findAcoes(jogoId);        } else            return findAcoesByCategoria(jogoId, categoria);    }    @Transactional    public List<Acao> findAcoes(int jogoId) {        Jogo jogo = find(jogoId);        return acaoService.findByJogo(jogo);    }    @Transactional    public List<Acao> findAcoesByCategoria(int jogoId, Class<?> categoria) {        Jogo jogo = find(jogoId);        return acaoService.findAllByCategoriaAndJogo(categoria, jogo);    }}