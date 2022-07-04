package me.samcefalo.sistemaiadbackend.repositories;

import me.samcefalo.sistemaiadbackend.models.Equipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EquipeRepository extends JpaRepository<Equipe, Integer>, JpaSpecificationExecutor<Equipe> {

    @Transactional(readOnly = true)
    List<Equipe> findAll(Specification specification);

    @Transactional(readOnly = true)
    Page<Equipe> findAll(Specification specification, Pageable pageable);

}
