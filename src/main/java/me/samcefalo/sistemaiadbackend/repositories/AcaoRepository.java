package me.samcefalo.sistemaiadbackend.repositories;

import me.samcefalo.sistemaiadbackend.models.Acao;
import me.samcefalo.sistemaiadbackend.models.Equipe;
import me.samcefalo.sistemaiadbackend.models.Jogador;
import me.samcefalo.sistemaiadbackend.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AcaoRepository extends JpaRepository<Acao, Integer> {

    @Transactional(readOnly = true)
    List<Acao> findAllByUser(User user);

    @Transactional(readOnly = true)
    Page<Acao> findAllByUser(Pageable pageable, User user);

    @Transactional(readOnly = true)
    List<Acao> findByEquipeAndUser(Equipe equipe, User user);

    @Transactional(readOnly = true)
    List<Acao> findByJogadorAndUser(Jogador jogador, User user);

    @Transactional(readOnly = true)
    Page<Acao> findByEquipeAndUser(Equipe equipe, Pageable pageable, User user);

    @Transactional(readOnly = true)
    Page<Acao> findByJogadorAndUser(Jogador jogador, Pageable pageable, User user);

    @Transactional(readOnly = true)
    List<Acao> findByJogadorAndExitoAndUser(Jogador jogador, boolean exito, User user);

}
