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
    List<Acao> findByEquipeAndGrauDificuldade(Equipe equipe, int grauDeDificuldade);

    @Transactional(readOnly = true)
    List<Acao> findByJogadorAndGrauDificuldade(Jogador jogador, int grauDeDificuldade);

    @Transactional(readOnly = true)
    List<Acao> findByTipo(String tipo);

    @Transactional(readOnly = true)
    List<Acao> findByJogadorAndExito(Jogador jogador, boolean exito);

    @Transactional(readOnly = true)
    List<Acao> findByJogadorAndTipo(Jogador jogador, String tipo);

    @Transactional(readOnly = true)
    List<Acao> findByJogadorAndTipoAndExito(Jogador jogador, String tipo, boolean exito);

    @Transactional(readOnly = true)
    List<Acao> findByJogadorAndTipoAndGrauDificuldade(Jogador jogador, String tipo, int grauDeDificuldade);

    @Transactional(readOnly = true)
    List<Acao> findByEquipeAndTipo(Equipe equipe, String tipo);

}
