package me.samcefalo.sistemaiadbackend.services;import me.samcefalo.sistemaiadbackend.domain.Acao;import me.samcefalo.sistemaiadbackend.repositories.AcaoRepository;import me.samcefalo.sistemaiadbackend.services.exceptions.ObjectNotFoundException;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;import java.util.List;@Servicepublic class AcaoService {    @Autowired    private AcaoRepository acaoRepository;    public Acao find(int id) {        return acaoRepository.findById(id).orElseThrow(                () -> new ObjectNotFoundException("Objecto não encontrado. ID: " + id + ", Tipo: " + Acao.class.getName()));    }    public Acao insert(Acao acao) {        return acaoRepository.save(acao);    }    public Acao update(Acao acao) {        Acao acaoNew = find(acao.getId());        updateData(acaoNew, acao);        return acaoRepository.save(acaoNew);    }    private void updateData(Acao acaoNew, Acao acao) {        acaoNew.setArea(acao.getArea().getId());        acaoNew.setExito(acao.isExito());        acaoNew.setJogador(acao.getJogador());        acaoNew.setGrauDificuldade(acao.getGrauDificuldade());        acaoNew.setEquipe(acao.getEquipe());    }    public void delete(int id) {        Acao acao = find(id);        delete(acao);    }    private void delete(Acao acao) {        acaoRepository.delete(acao);    }    public List<Acao> findAll() {        return acaoRepository.findAll();    }    /*    public Acao fromDTO(AcaoDTO acaoDTO) {    }    */}