package me.samcefalo.sistemaiadbackend.dtos;

import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO implements Serializable {

    private int id;
    private String username;
    private String email;
    private Set<Integer> userRoles = new HashSet<>();

}
