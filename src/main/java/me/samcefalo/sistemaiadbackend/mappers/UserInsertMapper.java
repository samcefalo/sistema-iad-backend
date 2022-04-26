package me.samcefalo.sistemaiadbackend.mappers;

import me.samcefalo.sistemaiadbackend.dtos.UserInsertDTO;
import me.samcefalo.sistemaiadbackend.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserInsertMapper extends MapperImpl<User, UserInsertDTO> {

    public UserInsertMapper() {
        super.configure(User.class, UserInsertDTO.class);
    }

}
