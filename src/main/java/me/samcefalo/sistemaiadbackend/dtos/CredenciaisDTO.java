package me.samcefalo.sistemaiadbackend.dtos;

import lombok.*;
import me.samcefalo.sistemaiadbackend.services.validation.constraints.UserInsert;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@UserInsert
public class CredenciaisDTO implements Serializable {

    private int id;
    private String email, senha;

}
