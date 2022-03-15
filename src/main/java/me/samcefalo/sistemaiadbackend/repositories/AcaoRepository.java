package me.samcefalo.sistemaiadbackend.repositories;

import me.samcefalo.sistemaiadbackend.domain.Acao;
import me.samcefalo.sistemaiadbackend.domain.Equipe;
import me.samcefalo.sistemaiadbackend.domain.Jogador;
import me.samcefalo.sistemaiadbackend.domain.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AcaoRepository extends JpaRepository<Acao, Integer> {

    @Transactional(readOnly = true)
    List<Acao> findByEquipe(Equipe equipe);

    @Transactional(readOnly = true)
    List<Acao> findByJogador(Jogador jogador);

    @Transactional(readOnly = true)
    List<Acao> findByJogo(Jogo jogo);

    @Transactional(readOnly = true)
    List<Acao> findByJogadorAndArea(Jogador jogador, int area);

    @Transactional(readOnly = true)
    List<Acao> findByEquipeAndArea(Equipe equipe, int area);

    @Transactional(readOnly = true)
    List<Acao> findByJogadorAndAreaAndGrauDificuldade(Jogador jogador, int area, int grauDeDificuldade);

    @Transactional(readOnly = true)
    List<Acao> findByEquipeAnAndGrauDificuldade(Equipe equipe, int grauDeDificuldade);

    @Transactional(readOnly = true)
    List<Acao> findByJogadorAndGrauDificuldade(Jogador jogador, int grauDeDificuldade);


}
