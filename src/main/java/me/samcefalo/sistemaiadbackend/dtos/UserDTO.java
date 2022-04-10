package me.samcefalo.sistemaiadbackend.dtos;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO implements Serializable {

    private int id;
    private String nome, email;

}
