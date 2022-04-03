package me.samcefalo.sistemaiadbackend.repositories;

import me.samcefalo.sistemaiadbackend.models.Equipe;
import me.samcefalo.sistemaiadbackend.models.Jogador;
import me.samcefalo.sistemaiadbackend.models.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface JogadorRepository extends JpaRepository<Jogador, Integer> {

    @Transactional(readOnly = true)
    List<Jogador> findByEquipe(Equipe equipe);

    @Transactional(readOnly = true)
    List<Jogador> findByEquipeAndTitular(Equipe equipe, boolean titular);

    @Transactional(readOnly = true)
    List<Jogador> findByJogos(Jogo jogo);

    @Transactional(readOnly = true)
    List<Jogador> findByJogosAndTitular(Jogo jogo, boolean titular);

    @Transactional(readOnly = true)
    List<Jogador> findByJogosAndEquipeAndTitular(Jogo jogo, Equipe equipe, boolean titular);

}
