package me.samcefalo.sistemaiadbackend.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import me.samcefalo.sistemaiadbackend.domain.enums.SituacaoJogo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Jogo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int situacaoJogo;

    @ManyToOne
    @JoinColumn(name = "equipe1_id")
    private Equipe equipe1;
    @ManyToOne
    @JoinColumn(name = "equipe2_id")
    private Equipe equipe2;

    @ManyToMany
    @JoinTable(name = "JOGO_JOGADOR",
            joinColumns = @JoinColumn(name = "jogo_id"),
            inverseJoinColumns = @JoinColumn(name = "jogador_id"))
    private Set<Jogador> jogadores = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "ACAO_JOGO")
    private Set<Acao> acoes = new HashSet<>();

    public SituacaoJogo getSituacaoJogo() {
        return SituacaoJogo.toEnum(situacaoJogo);
    }

}
