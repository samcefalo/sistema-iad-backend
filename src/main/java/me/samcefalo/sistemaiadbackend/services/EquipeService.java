package me.samcefalo.sistemaiadbackend.services;import me.samcefalo.sistemaiadbackend.domain.Acao;import me.samcefalo.sistemaiadbackend.domain.Equipe;import me.samcefalo.sistemaiadbackend.domain.Jogador;import me.samcefalo.sistemaiadbackend.domain.Jogo;import me.samcefalo.sistemaiadbackend.repositories.AcaoRepository;import me.samcefalo.sistemaiadbackend.repositories.EquipeRepository;import me.samcefalo.sistemaiadbackend.repositories.JogoFutsalRepository;import me.samcefalo.sistemaiadbackend.services.exceptions.ObjectNotFoundException;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.data.domain.Page;import org.springframework.data.domain.PageRequest;import org.springframework.data.domain.Sort;import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;import java.util.List;@Servicepublic class EquipeService {    @Autowired    private EquipeRepository equipeRepository;    @Autowired    private JogadorService jogadorService;    @Autowired    private TecnicoService tecnicoService;    @Autowired    private AcaoRepository acaoRepository;    @Autowired    private JogoFutsalRepository jogoRepository;    public Equipe find(int id) {        return equipeRepository.findById(id).orElseThrow(                () -> new ObjectNotFoundException("Objecto não encontrado. ID: " + id + ", Tipo: " + Equipe.class.getName()));    }    public Equipe insert(Equipe equipe) {        return equipeRepository.save(equipe);    }    public Equipe update(Equipe equipe) {        Equipe equipeNew = find(equipe.getId());        updateData(equipeNew, equipe);        return equipeRepository.save(equipeNew);    }    private void updateData(Equipe equipeNew, Equipe equipe) {        equipeNew.setNome(equipe.getNome());        equipeNew.setTecnico(equipe.getTecnico());        equipeNew.setJogadores(equipe.getJogadores());    }    public void delete(int id) {        Equipe equipe = find(id);        delete(equipe);    }    private void delete(Equipe equipe) {        if (equipe.getTecnico() != null) tecnicoService.delete(equipe.getTecnico().getId());        for (Jogador jogador : equipe.getJogadores()) {            jogadorService.delete(jogador.getId());        }        equipeRepository.delete(equipe);    }    @Transactional(readOnly = true)    public Page<Equipe> findPage(int page, int linesPerPage, String orderBy, String direction) {        PageRequest pageRequest = PageRequest.of(page, linesPerPage,                Sort.Direction.valueOf(direction.toUpperCase()), orderBy);        return equipeRepository.findAll(pageRequest);    }    @Transactional(readOnly = true)    public List<Equipe> findAll() {        return equipeRepository.findAll();    }    @Transactional(readOnly = true)    public List<Acao> findAcoes(int id) {        Equipe equipe = find(id);        return acaoRepository.findByEquipe(equipe);    }    @Transactional(readOnly = true)    public Page<Acao> findAcoesPage(int id, int page, int linesPerPage, String orderBy, String direction) {        Equipe equipe = find(id);        PageRequest pageRequest = PageRequest.of(page, linesPerPage,                Sort.Direction.valueOf(direction.toUpperCase()), orderBy);        return acaoRepository.findByEquipe(equipe, pageRequest);    }    @Transactional(readOnly = true)    public List<Jogo> findJogos(int id) {        Equipe equipe = find(id);        return jogoRepository.findByEquipes(equipe);    }    @Transactional(readOnly = true)    public Page<Jogo> findJogosPage(int id, int page, int linesPerPage, String orderBy, String direction) {        Equipe equipe = find(id);        PageRequest pageRequest = PageRequest.of(page, linesPerPage,                Sort.Direction.valueOf(direction.toUpperCase()), orderBy);        return jogoRepository.findByEquipes(equipe, pageRequest);    }}