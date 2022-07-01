package me.samcefalo.sistemaiadbackend.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import me.samcefalo.sistemaiadbackend.services.validation.constraints.Age;
import me.samcefalo.sistemaiadbackend.services.validation.constraints.Team;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AtletaDTO {

    private int id;
    @NotBlank(message = "O nome é obrigatório.")
    @Length(min = 3, max = 100, message = "Insira um nome com {min}-{max} caracteres.")
    private String nome;
    @JsonIgnore
    private UserDTO user;
    @Age
    private LocalDate data_nascimento;
    private int idade;
    @Min(value = 1, message = "O número é obrigatório.")
    private int numero;
    @Team
    private EquipeDTO equipe;
    @Min(value = 1, message = "O sexo é obrigatório.")
    private int sexo;

}
