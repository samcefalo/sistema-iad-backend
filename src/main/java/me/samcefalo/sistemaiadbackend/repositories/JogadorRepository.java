package me.samcefalo.sistemaiadbackend.repositories;

import me.samcefalo.sistemaiadbackend.domain.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JogadorRepository extends JpaRepository<Jogador, Integer> {

}
