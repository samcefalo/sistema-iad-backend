package me.samcefalo.sistemaiadbackend.services;import me.samcefalo.sistemaiadbackend.domain.Acao;import me.samcefalo.sistemaiadbackend.domain.Entidade;import me.samcefalo.sistemaiadbackend.domain.Jogador;import me.samcefalo.sistemaiadbackend.domain.Jogo;import me.samcefalo.sistemaiadbackend.repositories.AcaoRepository;import me.samcefalo.sistemaiadbackend.repositories.JogadorRepository;import me.samcefalo.sistemaiadbackend.repositories.JogoFutsalRepository;import me.samcefalo.sistemaiadbackend.services.exceptions.ObjectNotFoundException;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.data.domain.Page;import org.springframework.data.domain.PageRequest;import org.springframework.data.domain.Sort;import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;import java.util.List;@Servicepublic class JogadorService {    @Autowired    private JogadorRepository jogadorRepository;    @Autowired    private AcaoRepository acaoRepository;    @Autowired    private AcaoService acaoService;    @Autowired    private JogoFutsalRepository jogoRepository;    public Jogador find(int id) {        return jogadorRepository.findById(id).orElseThrow(                () -> new ObjectNotFoundException("Objecto não encontrado. ID: " + id + ", Tipo: " + Entidade.class.getName()));    }    public Jogador insert(Jogador jogador) {        return jogadorRepository.save(jogador);    }    public Jogador update(Jogador jogador) {        Jogador jogadorNew = find(jogador.getId());        updateData(jogadorNew, jogador);        return jogadorRepository.save(jogadorNew);    }    private void updateData(Jogador jogadorNew, Jogador jogador) {        jogadorNew.setNome(jogador.getNome());        jogadorNew.setEquipe(jogador.getEquipe());        jogadorNew.setNumero(jogador.getNumero());        jogadorNew.setExpulso(jogador.isExpulso());        jogadorNew.setTitular(jogador.isTitular());    }    public void delete(int id) {        Jogador jogador = find(id);        delete(jogador);    }    private void delete(Jogador jogador) {        for (Acao acao : jogador.getAcoes()) {            acaoService.delete(acao.getId());        }        jogadorRepository.delete(jogador);    }    @Transactional(readOnly = true)    public Page<Jogador> findPage(int page, int linesPerPage, String orderBy, String direction) {        PageRequest pageRequest = PageRequest.of(page, linesPerPage,                Sort.Direction.valueOf(direction.toUpperCase()), orderBy);        return jogadorRepository.findAll(pageRequest);    }    @Transactional(readOnly = true)    public List<Jogador> findAll() {        return jogadorRepository.findAll();    }    @Transactional    public List<Acao> findAcoes(int id) {        Jogador jogador = find(id);        return acaoRepository.findByJogador(jogador);    }    @Transactional(readOnly = true)    public Page<Acao> findAcoesPage(int id, int page, int linesPerPage, String orderBy, String direction) {        Jogador jogador = find(id);        PageRequest pageRequest = PageRequest.of(page, linesPerPage,                Sort.Direction.valueOf(direction.toUpperCase()), orderBy);        return acaoRepository.findByJogador(jogador, pageRequest);    }    @Transactional    public List<Jogo> findJogos(int id) {        Jogador jogador = find(id);        return jogoRepository.findByJogadores(jogador);    }    @Transactional(readOnly = true)    public Page<Jogo> findJogosPage(int id, int page, int linesPerPage, String orderBy, String direction) {        Jogador jogador = find(id);        PageRequest pageRequest = PageRequest.of(page, linesPerPage,                Sort.Direction.valueOf(direction.toUpperCase()), orderBy);        return jogoRepository.findByJogador(jogador, pageRequest);    }}