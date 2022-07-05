package me.samcefalo.sistemaiadbackend.repositories;

import me.samcefalo.sistemaiadbackend.models.Acao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AcaoRepository extends JpaRepository<Acao, Integer>, JpaSpecificationExecutor {

    @Transactional(readOnly = true)
    List<Acao> findAll(Specification specification);

    @Transactional(readOnly = true)
    Page<Acao> findAll(Specification specification, Pageable pageable);

}

