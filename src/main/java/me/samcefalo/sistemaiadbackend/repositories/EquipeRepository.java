package me.samcefalo.sistemaiadbackend.repositories;

import me.samcefalo.sistemaiadbackend.models.Equipe;
import me.samcefalo.sistemaiadbackend.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EquipeRepository extends JpaRepository<Equipe, Integer> {

    @Transactional(readOnly = true)
    List<Equipe> findAllByUser(User user);

    @Transactional(readOnly = true)
    Page<Equipe> findAllByUser(Pageable pageable, User user);

}
