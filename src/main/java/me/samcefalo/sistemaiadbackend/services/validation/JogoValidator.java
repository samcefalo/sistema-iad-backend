package me.samcefalo.sistemaiadbackend.services.validation;

import me.samcefalo.sistemaiadbackend.dtos.JogoDTO;
import me.samcefalo.sistemaiadbackend.repositories.JogoRepository;
import me.samcefalo.sistemaiadbackend.resources.exceptions.FieldMessage;
import me.samcefalo.sistemaiadbackend.services.validation.constraints.JogoValid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

@Component
public class JogoValidator implements ConstraintValidator<JogoValid, JogoDTO> {

    @Autowired
    private JogoRepository jogoRepository;

    @Override
    public void initialize(JogoValid ann) {
    }

    @Override
    public boolean isValid(JogoDTO jogo, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        //Jogo existente
        if (jogo == null || jogo.getId() == 0) {
            list.add(new FieldMessage("jogo", "Jogo inválido."));
        } else if (!jogoRepository.findById(jogo.getId()).isPresent()) {
            list.add(new FieldMessage("jogo", "Jogo " + jogo.getId() + " inválido."));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addConstraintViolation();
        }

        return list.isEmpty();
    }

}
