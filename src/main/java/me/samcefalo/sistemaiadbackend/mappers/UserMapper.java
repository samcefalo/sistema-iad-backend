package me.samcefalo.sistemaiadbackend.mappers;

import me.samcefalo.sistemaiadbackend.dtos.UserDTO;
import me.samcefalo.sistemaiadbackend.dtos.UserInsertDTO;
import me.samcefalo.sistemaiadbackend.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends MapperImpl<User, UserDTO> {

    public UserMapper() {
        super.configure(User.class, UserInsertDTO.class);
    }

}
