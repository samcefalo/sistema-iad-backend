package me.samcefalo.sistemaiadbackend.dtos;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonTypeName("JogoFutsal")
public class JogoFutsalDTO extends JogoDTO {

    @Override
    public int getLimite_jogador_titular() {
        return 5;
    }

}
