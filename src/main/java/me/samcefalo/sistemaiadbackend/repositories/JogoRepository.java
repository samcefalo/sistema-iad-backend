package me.samcefalo.sistemaiadbackend.repositories;

import me.samcefalo.sistemaiadbackend.models.Jogo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface JogoRepository extends JpaRepository<Jogo, Integer>, JpaSpecificationExecutor<Jogo> {

    @Transactional(readOnly = true)
    List<Jogo> findAll(Specification specification);

    @Transactional(readOnly = true)
    Page<Jogo> findAll(Specification specification, Pageable pageable);

    @Transactional(readOnly = true)
    void deleteAll(Specification specification);
}
