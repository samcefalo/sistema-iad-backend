package me.samcefalo.sistemaiadbackend.mappers;

import me.samcefalo.sistemaiadbackend.dtos.UserUpdateDTO;
import me.samcefalo.sistemaiadbackend.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserUpdateMapper extends MapperImpl<User, UserUpdateDTO> {

    public UserUpdateMapper() {
        super.configure(User.class, UserUpdateDTO.class);
    }

}
