package me.samcefalo.sistemaiadbackend.services.validation;

import me.samcefalo.sistemaiadbackend.repositories.JogadorRepository;
import me.samcefalo.sistemaiadbackend.services.exceptions.FieldMessage;
import me.samcefalo.sistemaiadbackend.services.validation.constraints.Jogador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

@Component
public class JogadorValidator implements ConstraintValidator<Jogador, Integer> {

    @Autowired
    private JogadorRepository jogadorRepository;

    @Override
    public void initialize(Jogador ann) {
    }

    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        //Equipe existente
        if (!jogadorRepository.findById(id).isPresent()) {
            list.add(new FieldMessage("jogadorId", "Jogador " + id + " inválido."));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addConstraintViolation();
        }

        return list.isEmpty();
    }

}
