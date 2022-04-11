package me.samcefalo.sistemaiadbackend.services.validation;

import lombok.NoArgsConstructor;
import me.samcefalo.sistemaiadbackend.dtos.UserDTO;
import me.samcefalo.sistemaiadbackend.repositories.UserRepository;
import me.samcefalo.sistemaiadbackend.resources.exceptions.FieldMessage;
import me.samcefalo.sistemaiadbackend.services.validation.constraints.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class UserValidator implements ConstraintValidator<User, UserDTO> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void initialize(User ann) {
    }

    @Override
    public boolean isValid(UserDTO userDTO, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            list.add(new FieldMessage("email", "Email já cadastrado."));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addConstraintViolation();
        }

        return list.isEmpty();
    }

}
