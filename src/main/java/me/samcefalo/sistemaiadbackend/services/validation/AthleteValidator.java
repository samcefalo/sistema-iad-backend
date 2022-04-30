package me.samcefalo.sistemaiadbackend.services.validation;

import me.samcefalo.sistemaiadbackend.dtos.AtletaDTO;
import me.samcefalo.sistemaiadbackend.repositories.AtletaRepository;
import me.samcefalo.sistemaiadbackend.resources.exceptions.objects.FieldMessage;
import me.samcefalo.sistemaiadbackend.services.validation.constraints.Athlete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

@Component
public class AthleteValidator implements ConstraintValidator<Athlete, AtletaDTO> {

    @Autowired
    private AtletaRepository atletaRepository;

    @Override
    public void initialize(Athlete ann) {
    }

    @Override
    public boolean isValid(AtletaDTO atleta, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        if (atleta == null || atleta.getId() == 0) {
            list.add(new FieldMessage("atleta", "Atleta inválido."));
        } else if (!atletaRepository.findById(atleta.getId()).isPresent()) {
            list.add(new FieldMessage("atleta", "Atleta " + atleta.getId() + " inválido."));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addConstraintViolation();
        }

        return list.isEmpty();
    }

}
