package me.samcefalo.sistemaiadbackend.services.validation;

import me.samcefalo.sistemaiadbackend.repositories.JogoRepository;
import me.samcefalo.sistemaiadbackend.resources.exceptions.FieldMessage;
import me.samcefalo.sistemaiadbackend.services.validation.constraints.Jogo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

@Component
public class JogoValidator implements ConstraintValidator<Jogo, Integer> {

    @Autowired
    private JogoRepository jogoRepository;

    @Override
    public void initialize(Jogo ann) {
    }

    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        //Equipe existente
        if (!jogoRepository.findById(id).isPresent()) {
            list.add(new FieldMessage("jogoId", "Jogo " + id + " inválido."));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addConstraintViolation();
        }

        return list.isEmpty();
    }

}
