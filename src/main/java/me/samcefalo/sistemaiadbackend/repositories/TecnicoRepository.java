package me.samcefalo.sistemaiadbackend.repositories;

import me.samcefalo.sistemaiadbackend.models.Tecnico;
import me.samcefalo.sistemaiadbackend.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {

    @Transactional(readOnly = true)
    List<Tecnico> findAllByUser(User user);

    @Transactional(readOnly = true)
    Page<Tecnico> findAllByUser(Pageable pageable, User user);

}
