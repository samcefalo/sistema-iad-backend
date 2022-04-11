package me.samcefalo.sistemaiadbackend.dtos;

import lombok.*;
import me.samcefalo.sistemaiadbackend.services.validation.constraints.User;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@User
public class CredenciaisDTO implements Serializable {

    private int id;
    private String email, senha;

}
