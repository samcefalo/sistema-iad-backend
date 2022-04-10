package me.samcefalo.sistemaiadbackend.services.validation;

import me.samcefalo.sistemaiadbackend.dtos.JogadorDTO;
import me.samcefalo.sistemaiadbackend.repositories.JogadorRepository;
import me.samcefalo.sistemaiadbackend.resources.exceptions.FieldMessage;
import me.samcefalo.sistemaiadbackend.services.validation.constraints.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

@Component
public class PlayerValidator implements ConstraintValidator<Player, JogadorDTO> {

    @Autowired
    private JogadorRepository jogadorRepository;

    @Override
    public void initialize(Player ann) {
    }

    @Override
    public boolean isValid(JogadorDTO jogador, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        //Jogador existente
        if (jogador == null || jogador.getId() == 0) {
            list.add(new FieldMessage("jogador", "Jogador inválido."));
        } else if (!jogadorRepository.findById(jogador.getId()).isPresent()) {
            list.add(new FieldMessage("jogador", "Jogador " + jogador.getId() + " inválido."));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addConstraintViolation();
        }

        return list.isEmpty();
    }

}
