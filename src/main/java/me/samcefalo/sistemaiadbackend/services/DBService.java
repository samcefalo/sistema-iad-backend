package me.samcefalo.sistemaiadbackend.services;

import me.samcefalo.sistemaiadbackend.domain.*;
import me.samcefalo.sistemaiadbackend.domain.enums.Area;
import me.samcefalo.sistemaiadbackend.domain.enums.SituacaoJogo;
import me.samcefalo.sistemaiadbackend.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

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
        passe.setArea(Area.OFENSIVO.getId());
        passe.setExito(true);
        passe.setGrauDificuldade(2);
        acaoRepository.save(passe);

        JogoFutsal jogoFutsal = new JogoFutsal();
        jogoFutsal.setSituacaoJogo(SituacaoJogo.INICIADO.getId());
        jogoRepository.save(jogoFutsal);

        passe.setJogo(jogoFutsal);
        jogoFutsal.getAcoes().add(passe);

        Tecnico tecnico = new Tecnico();
        tecnico.setNome("Teste");
        tecnico.getJogos().add(jogoFutsal);

        Jogador jogador = new Jogador();
        jogador.setNome("Samuel");
        jogador.setNumero(10);
        jogador.setTitular(true);
        jogador.setExpulso(false);

        Jogador jogador1 = new Jogador();
        jogador1.setNome("Zidane");
        jogador1.setNumero(9);
        jogador1.setTitular(true);
        jogador1.setExpulso(false);

        jogadorRepository.saveAll(Arrays.asList(jogador, jogador1));
        tecnicoRepository.save(tecnico);

        Equipe equipe = new Equipe();
        equipe.setNome("Corinthians");
        equipe.setTecnico(tecnico);

        jogoFutsal.getJogadores().add(jogador);

        jogador.setEquipe(equipe);
        jogador1.setEquipe(equipe);
        tecnico.setEquipe(equipe);
        passe.setEquipe(equipe);
        passe.setJogador(jogador);

        jogoFutsal.setEquipe1(equipe);

        equipeRepository.save(equipe);
        tecnicoRepository.save(tecnico);
        acaoRepository.save(passe);
        jogoRepository.save(jogoFutsal);
        jogadorRepository.saveAll(Arrays.asList(jogador, jogador1));

        System.out.println(jogadorRepository.findByEquipe(equipe));
        System.out.println(jogadorRepository.findByJogosAndTitular(jogoFutsal, true));
        System.out.println(jogadorRepository.findByJogosAndEquipeAndTitular(jogoFutsal, equipe, true));
        System.out.println(equipeRepository.findByJogadores(jogador));
        System.out.println(acaoRepository.findByJogadorAndArea(jogador, Area.DEFENSIVO.getId()));


    }


}
