package me.samcefalo.sistemaiadbackend.repositories;

import me.samcefalo.sistemaiadbackend.domain.Acao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcaoRepository extends JpaRepository<Acao, Integer> {

}
