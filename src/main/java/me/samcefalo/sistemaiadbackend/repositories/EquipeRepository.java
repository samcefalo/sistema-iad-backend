package me.samcefalo.sistemaiadbackend.repositories;

import me.samcefalo.sistemaiadbackend.domain.Equipe;
import me.samcefalo.sistemaiadbackend.domain.Jogador;
import me.samcefalo.sistemaiadbackend.domain.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface EquipeRepository extends JpaRepository<Equipe, Integer> {

    @Transactional(readOnly = true)
    Optional<Equipe> findByJogadores(Jogador jogador);

    @Transactional(readOnly = true)
    Optional<Equipe> findByTecnico(Tecnico tecnico);

}
