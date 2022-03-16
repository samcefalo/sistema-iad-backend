package me.samcefalo.sistemaiadbackend.services;import me.samcefalo.sistemaiadbackend.domain.Equipe;import me.samcefalo.sistemaiadbackend.repositories.EquipeRepository;import me.samcefalo.sistemaiadbackend.services.exceptions.ObjectNotFoundException;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;import java.util.List;@Servicepublic class EquipeService {    @Autowired    private EquipeRepository equipeRepository;    @Autowired    private TecnicoService tecnicoService;    public Equipe find(int id) {        return equipeRepository.findById(id).orElseThrow(                () -> new ObjectNotFoundException("Objecto não encontrado. ID: " + id + ", Tipo: " + Equipe.class.getName()));    }    public Equipe insert(Equipe equipe) {        return equipeRepository.save(equipe);    }    public Equipe update(Equipe equipe) {        Equipe equipeNew = find(equipe.getId());        updateData(equipeNew, equipe);        return equipeRepository.save(equipeNew);    }    private void updateData(Equipe equipeNew, Equipe equipe) {        equipeNew.setNome(equipe.getNome());        equipeNew.setTecnico(equipe.getTecnico());        equipeNew.setJogadores(equipe.getJogadores());    }    public void delete(int id) {        Equipe equipe = find(id);        delete(equipe);    }    private void delete(Equipe equipe) {        tecnicoService.delete(equipe.getTecnico().getId());        equipeRepository.delete(equipe);    }    public List<Equipe> findAll() {        return equipeRepository.findAll();    }}