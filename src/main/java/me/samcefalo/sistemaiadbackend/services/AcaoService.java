package me.samcefalo.sistemaiadbackend.services;import me.samcefalo.sistemaiadbackend.models.Acao;import me.samcefalo.sistemaiadbackend.repositories.AcaoRepository;import me.samcefalo.sistemaiadbackend.services.exceptions.AuthorizationException;import me.samcefalo.sistemaiadbackend.services.exceptions.ObjectNotFoundException;import me.samcefalo.sistemaiadbackend.specifications.AcaoSpecification;import me.samcefalo.sistemaiadbackend.specifications.criterias.AcaoCriteria;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.data.domain.Page;import org.springframework.data.domain.PageRequest;import org.springframework.data.domain.Sort;import org.springframework.data.jpa.domain.Specification;import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;import java.util.List;@Servicepublic class AcaoService {    @Autowired    private AcaoRepository acaoRepository;    @Autowired    private UserSecurityService userSecurityService;    public Acao find(int id) {        return acaoRepository.findById(id).orElseThrow(                () -> new ObjectNotFoundException("Objeto não encontrado. ID: " + id + ", Tipo: " + Acao.class.getSimpleName()));    }    public Acao insert(Acao acao) {        if (!userSecurityService.isAuth(acao)) {            throw new AuthorizationException("Acesso negado.");        }        return acaoRepository.save(acao);    }    public Acao update(Acao acao) {        Acao acaoDB = find(acao.getId());        if (!userSecurityService.isAuth(acaoDB)) {            throw new AuthorizationException("Acesso negado.");        }        updateData(acaoDB, acao);        return acaoRepository.save(acaoDB);    }    private void updateData(Acao acaoDB, Acao acao) {        acaoDB.setArea(acao.getArea());        acaoDB.setExito(acao.isExito());        acaoDB.setAtleta(acao.getAtleta());        acaoDB.setGrauDificuldade(acao.getGrauDificuldade());        acaoDB.setEquipe(acao.getEquipe());        acaoDB.setJogo(acao.getJogo());        acaoDB.setPlacar(acao.getPlacar());        acaoDB.setTempo(acao.getTempo());        acaoDB.setEtapa(acao.getEtapa());    }    public void delete(int id) {        Acao acao = find(id);        if (!userSecurityService.isAuth(acao)) {            throw new AuthorizationException("Acesso negado.");        }        delete(acao);    }    private void delete(Acao acao) {        acaoRepository.delete(acao);    }    @Transactional(readOnly = true)    public List<Acao> findAll(AcaoCriteria acaoCriteria) {        acaoCriteria.setUserId(userSecurityService.authenticatedUser().getId());        Specification specification = new AcaoSpecification(acaoCriteria);        return acaoRepository.findAll(specification);    }    @Transactional(readOnly = true)    public Page<Acao> findAllPage(AcaoCriteria acaoCriteria, int page, int linesPerPage, String orderBy, String direction) {        acaoCriteria.setUserId(userSecurityService.authenticatedUser().getId());        Specification specification = new AcaoSpecification(acaoCriteria);        System.out.println(acaoCriteria);        PageRequest pageRequest = PageRequest.of(page, linesPerPage,                Sort.Direction.valueOf(direction.toUpperCase()), orderBy);        return acaoRepository.findAll(specification, pageRequest);    }}