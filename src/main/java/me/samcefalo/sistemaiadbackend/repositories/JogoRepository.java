package me.samcefalo.sistemaiadbackend.repositories;

import me.samcefalo.sistemaiadbackend.models.Atleta;
import me.samcefalo.sistemaiadbackend.models.Equipe;
import me.samcefalo.sistemaiadbackend.models.Jogo;
import me.samcefalo.sistemaiadbackend.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface JogoRepository extends JpaRepository<Jogo, Integer> {

    @Transactional(readOnly = true)
    List<Jogo> findAllByUser(User user);

    @Transactional(readOnly = true)
    Page<Jogo> findAllByUser(Pageable pageable, User user);

    @Transactional(readOnly = true)
    List<Jogo> findByEquipesAndUser(Equipe equipe, User user);

    @Transactional(readOnly = true)
    List<Jogo> findByAtletasAndUser(Atleta atleta, User user);

    @Transactional(readOnly = true)
    Page<Jogo> findByEquipesAndUser(Equipe equipe, Pageable pageable, User user);

    @Transactional(readOnly = true)
    Page<Jogo> findByAtletasAndUser(Atleta atleta, Pageable pageable, User user);

    /*
    @Transactional(readOnly = true)
    @Query("SELECT a FROM Jogo a WHERE TYPE(a) = :categoria AND a.user = :user")
    Page<Jogo> findAllCategoriaByUser(@Param(value = "categoria") Class<?> categoria, Pageable pageable, @Param(value = "user") User user);
*/
    @Transactional(readOnly = true)
    Page<Jogo> findAllByEsporteAndUser(int esporte, Pageable pageable, User user);

}
