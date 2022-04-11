package me.samcefalo.sistemaiadbackend.mappers.implementations;

import me.samcefalo.sistemaiadbackend.dtos.UserInsertDTO;
import me.samcefalo.sistemaiadbackend.mappers.UserInsertMapper;
import me.samcefalo.sistemaiadbackend.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserInsertMapperImpl extends AbstractMapperImpl<User, UserInsertDTO> implements UserInsertMapper {

    public UserInsertMapperImpl() {
        super.configure(User.class, UserInsertDTO.class);
    }

}
