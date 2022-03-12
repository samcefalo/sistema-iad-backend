package me.samcefalo.sistemaiadbackend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.samcefalo.sistemaiadbackend.domain.enums.SituacaoJogo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Jogo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    private int situacaoJogo;

    @ManyToOne
    @JoinColumn(name = "equipe1_id")
    private Equipe equipe1;
    @ManyToOne
    @JoinColumn(name = "equipe2_id")
    private Equipe equipe2;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "JOGO_JOGADOR",
            joinColumns = @JoinColumn(name = "jogo_id"),
            inverseJoinColumns = @JoinColumn(name = "jogador_id"))
    private List<Jogador> jogadores = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "ACAO_JOGO")
    private List<Acao> acoes = new ArrayList<>();

    public SituacaoJogo getSituacaoJogo() {
        return SituacaoJogo.toEnum(situacaoJogo);
    }

}
