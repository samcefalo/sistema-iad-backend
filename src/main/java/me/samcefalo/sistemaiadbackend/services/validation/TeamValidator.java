package me.samcefalo.sistemaiadbackend.services.validation;

import lombok.NoArgsConstructor;
import me.samcefalo.sistemaiadbackend.dtos.EquipeDTO;
import me.samcefalo.sistemaiadbackend.repositories.EquipeRepository;
import me.samcefalo.sistemaiadbackend.resources.exceptions.FieldMessage;
import me.samcefalo.sistemaiadbackend.services.validation.constraints.Team;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class TeamValidator implements ConstraintValidator<Team, EquipeDTO> {

    @Autowired
    private EquipeRepository equipeRepository;

    @Override
    public void initialize(Team ann) {
    }

    @Override
    public boolean isValid(EquipeDTO equipe, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();
        //Equipe existente
        if (equipe == null || equipe.getId() == 0) {
            list.add(new FieldMessage("equipes", "Equipe inválida."));
        } else if (!equipeRepository.findById(equipe.getId()).isPresent()) {
            list.add(new FieldMessage("equipes", "Equipe " + equipe.getId() + " inválida."));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addConstraintViolation();
        }

        return list.isEmpty();
    }

}
