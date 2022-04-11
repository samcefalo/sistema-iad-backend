package me.samcefalo.sistemaiadbackend.mappers.implementations;

import me.samcefalo.sistemaiadbackend.dtos.UserDTO;
import me.samcefalo.sistemaiadbackend.dtos.UserInsertDTO;
import me.samcefalo.sistemaiadbackend.mappers.UserMapper;
import me.samcefalo.sistemaiadbackend.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl extends AbstractMapperImpl<User, UserDTO> implements UserMapper {

    public UserMapperImpl() {
        super.configure(User.class, UserDTO.class);
        super.configure(User.class, UserInsertDTO.class);
    }

}
