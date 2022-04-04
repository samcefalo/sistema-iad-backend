package me.samcefalo.sistemaiadbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@tipo")
public abstract class Jogo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int situacaoJogo;

    @Transient
    private int limite_jogador_titular;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "EQUIPE_JOGO",
            joinColumns = @JoinColumn(name = "jogo_id"),
            inverseJoinColumns = @JoinColumn(name = "equipe_id"))
    private Set<Equipe> equipes = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "JOGADOR_JOGO",
            joinColumns = @JoinColumn(name = "jogo_id"),
            inverseJoinColumns = @JoinColumn(name = "jogador_id"))
    private Set<Jogador> jogadores = new HashSet<>();

    @OneToMany(mappedBy = "jogo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Acao> acoes = new HashSet<>();

    @JsonIgnore
    @Transient
    public int getGols(Equipe equipe) {
        return acoes.stream()
                .filter(acao -> acao.getEquipe().getId() == equipe.getId())
                .filter(acao -> acao instanceof Finalizacao)
                .filter(acao -> acao.isExito() && ((Finalizacao) acao).isGol())
                .collect(Collectors.toList()).size();
    }

}
