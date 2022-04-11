package me.samcefalo.sistemaiadbackend.services.validation;

import lombok.NoArgsConstructor;
import me.samcefalo.sistemaiadbackend.dtos.UserInsertDTO;
import me.samcefalo.sistemaiadbackend.repositories.UserRepository;
import me.samcefalo.sistemaiadbackend.resources.exceptions.FieldMessage;
import me.samcefalo.sistemaiadbackend.services.validation.constraints.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class UserValidator implements ConstraintValidator<User, UserInsertDTO> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void initialize(User ann) {
    }

    @Override
    public boolean isValid(UserInsertDTO userInsertDTO, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        if (userRepository.findByEmail(userInsertDTO.getEmail()).isPresent()) {
            list.add(new FieldMessage("email", "Este email já está cadastrado."));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }

        return list.isEmpty();
    }

}
