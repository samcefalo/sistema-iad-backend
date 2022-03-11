package me.samcefalo.sistemaiadbackend.repositories;

import me.samcefalo.sistemaiadbackend.domain.Entidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntidadeRepository extends JpaRepository<Entidade, Integer> {

}
