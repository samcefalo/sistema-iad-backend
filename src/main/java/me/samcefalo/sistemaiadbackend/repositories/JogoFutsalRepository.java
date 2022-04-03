package me.samcefalo.sistemaiadbackend.repositories;

import me.samcefalo.sistemaiadbackend.models.Equipe;
import me.samcefalo.sistemaiadbackend.models.Jogador;
import me.samcefalo.sistemaiadbackend.models.Jogo;
import me.samcefalo.sistemaiadbackend.models.JogoFutsal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    Page<Jogo> findByEquipes(Equipe equipe, Pageable pageable);

    @Transactional(readOnly = true)
    Page<Jogo> findByJogadores(Jogador jogador, Pageable pageable);

}
