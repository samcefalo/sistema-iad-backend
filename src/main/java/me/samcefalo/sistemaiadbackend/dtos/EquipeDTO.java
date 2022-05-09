package me.samcefalo.sistemaiadbackend.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EquipeDTO implements Serializable {

    private int id;
    @NotBlank(message = "O nome é obrigatório.")
    @Length(min = 3, max = 100, message = "Insira um nome com {min}-{max} caracteres.")
    private String nome;
    @JsonIgnore
    private UserDTO user;

}
