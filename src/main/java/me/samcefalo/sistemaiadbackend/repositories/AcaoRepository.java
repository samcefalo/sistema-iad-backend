package me.samcefalo.sistemaiadbackend.repositories;

import me.samcefalo.sistemaiadbackend.domain.Acao;
import me.samcefalo.sistemaiadbackend.domain.Equipe;
import me.samcefalo.sistemaiadbackend.domain.Jogador;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    Page<Acao> findByEquipe(Equipe equipe, Pageable pageable);

    @Transactional(readOnly = true)
    Page<Acao> findByJogador(Jogador jogador, Pageable pageable);

    @Transactional(readOnly = true)
    List<Acao> findByJogadorAndExito(Jogador jogador, boolean exito);

}
