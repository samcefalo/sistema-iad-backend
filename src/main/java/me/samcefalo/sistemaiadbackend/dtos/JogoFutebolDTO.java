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
@JsonTypeName("JogoFutebol")
public class JogoFutebolDTO extends JogoDTO {

    @Override
    public int getLimite_jogador_titular() {
        return 11;
    }

}
