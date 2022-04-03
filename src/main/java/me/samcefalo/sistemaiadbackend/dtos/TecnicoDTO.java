package me.samcefalo.sistemaiadbackend.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import me.samcefalo.sistemaiadbackend.services.validation.constraints.EquipeValid;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TecnicoDTO extends EntidadeDTO {

    @EquipeValid
    private EquipeDTO equipe;

}
