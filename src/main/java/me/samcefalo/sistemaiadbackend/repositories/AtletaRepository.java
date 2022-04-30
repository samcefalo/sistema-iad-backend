package me.samcefalo.sistemaiadbackend.repositories;

import me.samcefalo.sistemaiadbackend.models.Atleta;
import me.samcefalo.sistemaiadbackend.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AtletaRepository extends JpaRepository<Atleta, Integer> {

    @Transactional(readOnly = true)
    List<Atleta> findAllByUser(User user);

    @Transactional(readOnly = true)
    Page<Atleta> findAllByUser(Pageable pageable, User user);

}
