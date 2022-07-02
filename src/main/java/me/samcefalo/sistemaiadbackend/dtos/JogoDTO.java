package me.samcefalo.sistemaiadbackend.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import me.samcefalo.sistemaiadbackend.services.validation.constraints.Team;

import javax.validation.constraints.Min;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JogoDTO implements Serializable {

    private int id;
    @Min(value = 1, message = "O Esporte é obrigatório.")
    private int esporte;
    @JsonIgnore
    private UserDTO user;
    @Team
    private Set<EquipeDTO> equipes = new HashSet<>();
    private LocalDate data;
    private String nome;

}
