package me.samcefalo.sistemaiadbackend.domain;

import lombok.Data;
import me.samcefalo.sistemaiadbackend.domain.enums.SituacaoJogo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Jogo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    private SituacaoJogo situacaoJogo;

    @ManyToOne
    @JoinColumn(name = "equipe1_id")
    private Equipe equipe1;
    @ManyToOne
    @JoinColumn(name = "equipe2_id")
    private Equipe equipe2;

    @ElementCollection
    @CollectionTable(name = "ACAO_JOGO")
    private List<Acao> acoes = new ArrayList<>();


}
