package me.samcefalo.sistemaiadbackend.mappers.implementations;

import me.samcefalo.sistemaiadbackend.dtos.UserUpdateDTO;
import me.samcefalo.sistemaiadbackend.mappers.UserUpdateMapper;
import me.samcefalo.sistemaiadbackend.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserUpdateMapperImpl extends AbstractMapperImpl<User, UserUpdateDTO> implements UserUpdateMapper {

    public UserUpdateMapperImpl() {
        super.configure(User.class, UserUpdateDTO.class);
    }

}
