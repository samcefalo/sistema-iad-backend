package me.samcefalo.sistemaiadbackend.repositories;

import me.samcefalo.sistemaiadbackend.models.Atleta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AtletaRepository extends JpaRepository<Atleta, Integer>, JpaSpecificationExecutor<Atleta> {

    @Transactional(readOnly = true)
    List<Atleta> findAll(Specification specification);

    @Transactional(readOnly = true)
    Page<Atleta> findAll(Specification specification, Pageable pageable);

    @Transactional(readOnly = true)
    void deleteAll(Specification specification);

}
