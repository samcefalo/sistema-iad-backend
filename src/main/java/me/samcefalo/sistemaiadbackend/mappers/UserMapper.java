package me.samcefalo.sistemaiadbackend.mappers;

import me.samcefalo.sistemaiadbackend.dtos.UserDTO;
import me.samcefalo.sistemaiadbackend.models.User;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper extends AbstractMapper<User, UserDTO> {

}
