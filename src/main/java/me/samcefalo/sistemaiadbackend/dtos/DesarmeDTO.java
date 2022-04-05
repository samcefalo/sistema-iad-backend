package me.samcefalo.sistemaiadbackend.dtos;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonTypeName("Desarme")
public class DesarmeDTO extends AcaoDTO {

    private boolean posseDeBola;

    @Override
    public int getMaxPontuacao() {
        return 3;
    }

}
