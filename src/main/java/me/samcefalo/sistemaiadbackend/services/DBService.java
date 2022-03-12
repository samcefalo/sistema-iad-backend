package me.samcefalo.sistemaiadbackend.services;

import me.samcefalo.sistemaiadbackend.domain.*;
import me.samcefalo.sistemaiadbackend.domain.enums.Area;
import me.samcefalo.sistemaiadbackend.domain.enums.SituacaoJogo;
import me.samcefalo.sistemaiadbackend.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DBService {

    @Autowired
    private EquipeRepository equipeRepository;
    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private JogoRepository jogoRepository;
    @Autowired
    private AcaoRepository acaoRepository;
    @Autowired
    private JogadorRepository jogadorRepository;

    public void startTestDatas() {
        Passe passe = new Passe();
        passe.setArea(Area.DEFENSIVO.getId());
        passe.setExito(true);
        passe.setGrauDificuldade(1);
        acaoRepository.save(passe);

        JogoFutsal jogoFutsal = new JogoFutsal();
        jogoFutsal.setSituacaoJogo(SituacaoJogo.INICIADO.getId());
        jogoRepository.save(jogoFutsal);

        passe.setJogo(jogoFutsal);
        jogoFutsal.getAcoes().add(passe);

        Tecnico tecnico = new Tecnico();
        tecnico.setNome("Ratinho");
        tecnico.getJogos().add(jogoFutsal);

        Jogador jogador = new Jogador();
        jogador.setNome("Samuel");
        jogador.setNumero(10);
        jogador.setTitular(true);
        jogador.setExpulso(false);

        jogadorRepository.save(jogador);
        tecnicoRepository.save(tecnico);

        Equipe equipe = new Equipe("Corinthians");
        equipe.setTecnico(tecnico);

        jogoFutsal.getJogadores().add(jogador);

        jogador.setEquipe(equipe);
        tecnico.setEquipe(equipe);
        passe.setEquipe(equipe);
        passe.setJogador(jogador);

        equipeRepository.save(equipe);
        tecnicoRepository.save(tecnico);
        acaoRepository.save(passe);
        jogoRepository.save(jogoFutsal);
        jogadorRepository.save(jogador);

        System.out.println("teste");
    }

}
