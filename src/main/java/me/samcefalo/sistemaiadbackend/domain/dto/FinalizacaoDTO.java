package me.samcefalo.sistemaiadbackend.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class FinalizacaoDTO extends AcaoDTO {

    private boolean gol;

}
