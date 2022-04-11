package me.samcefalo.sistemaiadbackend.mappers;

import me.samcefalo.sistemaiadbackend.dtos.UserInsertDTO;
import me.samcefalo.sistemaiadbackend.models.User;
import org.springframework.stereotype.Component;

@Component
public interface UserInsertMapper extends AbstractMapper<User, UserInsertDTO> {

}
