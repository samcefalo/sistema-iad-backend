package me.samcefalo.sistemaiadbackend.dtos;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserInsertDTO extends UserDTO {

    @NotBlank(message = "A senha é obrigatória.")
    private String senha;

}
