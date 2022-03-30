package me.samcefalo.sistemaiadbackend.services.validation;

import lombok.NoArgsConstructor;
import me.samcefalo.sistemaiadbackend.repositories.EquipeRepository;
import me.samcefalo.sistemaiadbackend.resources.exceptions.FieldMessage;
import me.samcefalo.sistemaiadbackend.services.validation.constraints.Equipe;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class EquipeValidator implements ConstraintValidator<Equipe, Integer> {

    @Autowired
    private EquipeRepository equipeRepository;

    @Override
    public void initialize(Equipe ann) {
    }

    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();
        //Equipe existente
        if (!equipeRepository.findById(id).isPresent()) {
            list.add(new FieldMessage("equipeId", "Equipe " + id + " inválida."));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addConstraintViolation();
        }

        return list.isEmpty();
    }

}
