package me.samcefalo.sistemaiadbackend.mappers;

import me.samcefalo.sistemaiadbackend.dtos.UserUpdateDTO;
import me.samcefalo.sistemaiadbackend.models.User;
import org.springframework.stereotype.Component;

@Component
public interface UserUpdateMapper extends AbstractMapper<User, UserUpdateDTO> {

}
