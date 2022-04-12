package me.samcefalo.sistemaiadbackend.repositories;

import me.samcefalo.sistemaiadbackend.models.Equipe;
import me.samcefalo.sistemaiadbackend.models.Jogador;
import me.samcefalo.sistemaiadbackend.models.Jogo;
import me.samcefalo.sistemaiadbackend.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface JogoRepository extends JpaRepository<Jogo, Integer> {

    @Transactional(readOnly = true)
    Page<Jogo> findAllByUser(Pageable pageable, User user);

    @Transactional(readOnly = true)
    List<Jogo> findByEquipesAndUser(Equipe equipe, User user);

    @Transactional(readOnly = true)
    List<Jogo> findByJogadoresAndUser(Jogador jogador, User user);

    @Transactional(readOnly = true)
    Page<Jogo> findByEquipesAndUser(Equipe equipe, Pageable pageable, User user);

    @Transactional(readOnly = true)
    Page<Jogo> findByJogadoresAndUser(Jogador jogador, Pageable pageable, User user);

    @Transactional(readOnly = true)
    @Query("SELECT a FROM Jogo a WHERE TYPE(a) = :categoria AND a.user = :user")
    Page<Jogo> findAllCategoriaByUser(@Param(value = "categoria") Class<?> categoria, Pageable pageable, @Param(value = "user") User user);

}
