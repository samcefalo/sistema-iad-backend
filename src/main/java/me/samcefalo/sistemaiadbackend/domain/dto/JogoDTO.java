package me.samcefalo.sistemaiadbackend.domain.dto;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@tipo")
public abstract class JogoDTO implements Serializable {

    private int id, situacaoJogo;
    private Set<EquipeDTO> equipes = new HashSet<>();

}
