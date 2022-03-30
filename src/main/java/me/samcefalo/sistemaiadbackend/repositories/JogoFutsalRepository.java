package me.samcefalo.sistemaiadbackend.repositories;

import me.samcefalo.sistemaiadbackend.domain.Equipe;
import me.samcefalo.sistemaiadbackend.domain.Jogador;
import me.samcefalo.sistemaiadbackend.domain.Jogo;
import me.samcefalo.sistemaiadbackend.domain.JogoFutsal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface JogoFutsalRepository extends JpaRepository<JogoFutsal, Integer> {

    @Transactional(readOnly = true)
    List<Jogo> findByEquipes(Equipe equipe);

    @Transactional(readOnly = true)
    List<Jogo> findByJogadores(Jogador jogador);

    @Transactional(readOnly = true)
    Page<Jogo> findByEquipes(Equipe equipe, PageRequest pageRequest);

    @Transactional(readOnly = true)
    Page<Jogo> findByJogador(Jogador jogador, PageRequest pageRequest);

}
