package me.samcefalo.sistemaiadbackend.services;import me.samcefalo.sistemaiadbackend.domain.Acao;import me.samcefalo.sistemaiadbackend.domain.JogoFutsal;import me.samcefalo.sistemaiadbackend.repositories.JogoRepository;import me.samcefalo.sistemaiadbackend.services.exceptions.ObjectNotFoundException;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;import java.util.List;import java.util.stream.Collectors;@Servicepublic class JogoFutsalService {    @Autowired    private JogoRepository jogoRepository;    @Autowired    private AcaoService acaoService;    public JogoFutsal find(int id) {        return findAll().stream()                .filter(jogoFutsal -> jogoFutsal.getId() == id)                .findFirst()                .orElseThrow(() -> new ObjectNotFoundException("Objecto não encontrado. ID: " + id + ", Tipo: " + JogoFutsal.class.getName()));        /*return (JogoFutsal) jogoRepository.findById(id)                .orElseThrow(() -> new ObjectNotFoundException("Objecto não encontrado. ID: " + id + ", Tipo: " + JogoFutsal.class.getName()));        */    }    public JogoFutsal insert(JogoFutsal jogoFutsal) {        return jogoRepository.save(jogoFutsal);    }    public JogoFutsal update(JogoFutsal jogoFutsal) {        JogoFutsal jogoNew = find(jogoFutsal.getId());        updateData(jogoNew, jogoFutsal);        return jogoRepository.save(jogoNew);    }    private void updateData(JogoFutsal jogoNew, JogoFutsal jogoFutsal) {        jogoNew.setSituacaoJogo(jogoFutsal.getSituacaoJogo());        jogoNew.getEquipes().addAll(jogoFutsal.getEquipes());        jogoNew.setAcoes(jogoFutsal.getAcoes());    }    public void delete(int id) {        JogoFutsal jogoFutsal = find(id);        delete(jogoFutsal);    }    private void delete(JogoFutsal jogoFutsal) {        for (Acao acao : jogoFutsal.getAcoes()) {            acaoService.delete(acao.getId());        }        jogoRepository.delete(jogoFutsal);    }    public List<JogoFutsal> findAll() {        return jogoRepository.findAll().stream()                .filter(jogo -> jogo instanceof JogoFutsal)                .map(JogoFutsal.class::cast)                .collect(Collectors.toList());        //return (List<JogoFutsal>) (List<?>) jogoRepository.findAll();    }}